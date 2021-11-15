package ca.bookstore3005.project.forms;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {
  
  @NonNull
  String email;
  @NonNull
  String password;
}
