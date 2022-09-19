package com.edu.hutech.major.controller;

import com.edu.hutech.major.global.GlobalData;
import com.edu.hutech.major.model.DeliveryAddress;
import com.edu.hutech.major.model.Product;
import com.edu.hutech.major.model.User;
import com.edu.hutech.major.model.UserRole;
import com.edu.hutech.major.service.ProductService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    HttpSession session;
    
    @ModelAttribute("deliveryAddress")
	public DeliveryAddress initModel() {
		return new DeliveryAddress();
	}
    
    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        UserRole u = (UserRole) session.getAttribute("user");

     
        return "cart";
    }//page cart

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }//click add from page viewProduct

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    } // delete 1 product

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        //model.addAttribute("cart", GlobalData.cart);
        return "checkout";
    } // checkout totalPrice
}
