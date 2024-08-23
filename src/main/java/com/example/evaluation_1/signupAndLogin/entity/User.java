//package com.example.evaluation_1.signupAndLogin.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//
//@Data
//@Builder
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "Users")
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @NotEmpty(message = "FirstName is required")
//    @Column(name = "first_name")
//    private String firstName;
//
//    @NotEmpty(message = "LastName is required")
//    @Column(name = "last_name")
//    private String lastName;
//
//    @NotEmpty(message = "email is required")
//    @Email(message = "Email should be valid")
//    @Column(unique = true, name = "user_email")
//    private String email;
//
//    @NotEmpty(message = "password is required")
//    @Column(name = "user_password")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
////    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}",
////            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
//    private String password;
//
//    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @Column(name = "date_of_birth")
//    private String dob;
//
//    @Column(name = "profile_picture_url")
//    private String profilePictureUrl;
//
//    @CreationTimestamp
//    @Column(updatable = false, name = "created_at")
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private Date updatedAt;
//
//    @Column(name = "user_Token")
//    private String token;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
//
//    @Override
//    public String getUsername() {
//        return firstName;
//    }
//}
package com.example.evaluation_1.signupAndLogin.entity;

import com.example.evaluation_1.chat.entity.ChatRoom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true, name = "user_email")
    private String email;

    @NotEmpty(message = "Password is required")
    @Column(name = "user_password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "user_token")
    private String token;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
