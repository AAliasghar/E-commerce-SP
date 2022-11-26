package com.simplilearn.sportyShoe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class SportyShoeController {


List<Item> items = new ArrayList<>();
List<Login> logins = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam ( required = false) String id) {  // Requestparam not always required
        //Binding Item object form.html
        model.addAttribute("categories", Constants.CATEGORIES);
        // If not finding index for the id then return new item otherweise return the item based on index
        int index = getIndexFromId(id);
        model.addAttribute("item",index == Constants.Not_Found ? new Item(): items.get(index));

        int indexLogin = getIndexFromIdLogin(id);
        model.addAttribute("login",indexLogin == Constants.Not_Found ? new Login(): logins.get(indexLogin));
        return "form";

    }

   
    // Creating method to handle submit botton
    @PostMapping("/submitItem")
    public String handleSubmit(Item item, RedirectAttributes redirectAttributes){
        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index==Constants.Not_Found) {
            items.add(item);    
        } else if (within5Days(item.getDate(), items.get(index).getDate())) { // If True
            items.set(index, item);
        } else { // If not true
           status = Constants.FAILED_STATUS;
        }
        // Using flashattribute to get the date
        redirectAttributes.addFlashAttribute("status", status); 
        return "redirect:/inventory";
    }


    @GetMapping("/login")
    public String login(Model model, @RequestParam ( required = false) String id){
        //int index = getIndexFromId(id);
        //Binding login object to view login.html
        int index = getIndexFromIdLogin(id);
        model.addAttribute("login",index == Constants.Not_Found ? new Login(): logins.get(index));
       
        return "login";
    }

    // Creating method to handle SignIn botton
    @PostMapping("/signIn")
    public String handleSignin(Login login, RedirectAttributes redirectAttributes){
        int index = getIndexFromIdLogin(login.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index==Constants.Not_Found) {
            logins.add(login);    
        }  else { // If not true
           status = Constants.FAILED_STATUS;
        }
        // Using flashattribute to get the date
        redirectAttributes.addFlashAttribute("status", status); 
        return "redirect:/inventory";
    }


    @GetMapping("/inventory")
    public String getInventory(Model model){
        // Populate invetory with data comes from items Arraylist
        model.addAttribute("items", items);
        model.addAttribute("logins", logins);
        return "inventory";
    }

    @GetMapping("/changepass")
    public String changePass(Model model,@RequestParam ( required = false) String id){
        int indexLogin = getIndexFromIdLogin(id);
        model.addAttribute("login",indexLogin == Constants.Not_Found ? new Login(): logins.get(indexLogin));
        
        return "changepass";
    }

 // Creating method to handle update botton password change
 @PostMapping("/update")
 public String handleUpdate(RedirectAttributes redirectAttributes){
      /*int index = getIndexFromPassLogin(login.getPassword());
        String status = Constants.SUCCESS_STATUS;
        if (pass==Constants.Not_Found) {
            status = Constants.FAILED_STATUS;   
        }  else { // If not true
           status = Constants.FAILED_STATUS;
        } Login login, RedirectAttributes redirectAttributes
        // Using flashattribute to get the date  */
        redirectAttributes.addFlashAttribute("status", Constants.SUCCESS_PASS_CHANGE);
     return "redirect:/";
 }


    //Get index from ID to evaluate existance of ID ITEM 
    public int getIndexFromId(String id){
        for ( int i=0;i<items.size();i++ ){

            if (items.get(i).getId().equals(id)) {
            return i; // Returning index of ID if it matches
            }
        }
        return Constants.Not_Found;
    }

    //Get index from ID to evaluate existance of ID LOGIN 
    public int getIndexFromIdLogin(String id){
        for ( int i=0;i<logins.size();i++ ){

            if (logins.get(i).getId().equals(id)) {
            return i; // Returning index of ID if it matches
            }
        }
        return Constants.Not_Found;
    }


      //Get index from ID to evaluate existance of ID LOGIN 
      public int getIndexFromPassLogin(String password){
        for ( int i=0;i<logins.size();i++ ){

            if (logins.get(i).getPassword().equals(password)) {
            return i; // Returning index of ID if it matches
            }
        }
        return Constants.Not_Found;
    }

    // Logic for case when client wants to update the date and is before existing date
    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }
   
}


