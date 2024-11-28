package com.example.maternityhome.controller;

import com.example.maternityhome.model.Post;
import com.example.maternityhome.model.User;
import com.example.maternityhome.service.PostService;
import com.example.maternityhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String blogHome(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "blog_list"; // Template for blog home page (blog_list.html)
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "blog_admin"; // Template for admin panel of the blog (blog_admin.html)
    }

    @GetMapping("/admin/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "blog_add"; // Template for creating a new post (blog_add.html)
    }

    @PostMapping("/admin/new")
    public String createPost(@ModelAttribute Post post) {
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now())); // Устанавливаем дату создания на текущее время.
        postService.save(post);
        return "redirect:/blog/admin"; // Перенаправляем на админскую панель после создания поста.
    }

    @GetMapping("/admin/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "edit_blog"; // Template for editing a post (edit_blog.html)
    }

    @PostMapping("/admin/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        post.setId(id); // Set ID for updating existing post.
        postService.save(post);
        return "redirect:/blog/admin"; // Redirect to admin panel after editing a post.
    }

    @PostMapping("/admin/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/blog/admin"; // Redirect to admin panel after deleting a post.
    }
}