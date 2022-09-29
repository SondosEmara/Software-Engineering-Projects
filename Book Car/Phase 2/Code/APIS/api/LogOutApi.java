package com.example.Phase2.api;
import com.example.Phase2.Service.UserManager;
import com.example.Phase2.model.LogOut;
import com.example.Phase2.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/LogOut")
@RestController
public class LogOutApi {

    @PostMapping("accountLogOut")
    public String LogOut(@RequestBody LogOut user) {
        try {
            User user1 = UserManager.search(user.getUserId());
            user1.setStatus("Offline");
            UserManager.updateUser(user1, user1.getUserId());
            return "Success Log Out";
        } catch (Exception error) {
            return error.getMessage();
        }
    }
}
