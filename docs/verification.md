## User verification description
[back to README](./../README.md)

The application incorporates a streamlined user authentication and registration interface, enhanced by a forget password feature. For seamless functionality, external mail SMTP services are employed to execute specific user operations, such as confirming registration or facilitating password resets.

To ensure security and precision in various tasks, the application utilizes distinct tokens, each serving a unique purpose:
* **REGISTRATION token** - validates the confirmation of registration.
* **RESET-PASSWORD token** - facilitates the secure resetting of passwords.
* **SESSION token** - manages user sessions effectively.

Tokens associated with registration or password reset are designed for single-use, adding an extra layer of security. In the case of SESSION tokens, each token remains active for a duration of 15 minutes. Beyond this timeframe, the token expires, rendering it invalid for further use.

Password integrity is a paramount focus, with the application enforcing the following criteria for password validity:
* Minimum length of 8 characters.
* Inclusion of at least one digit.
* Presence of an uppercase letter.
* Presence of a lowercase letter.

To safeguard user credentials, the application implements a fundamental hashing system for passwords. This security measure ensures that sensitive information remains protected and confidential throughout user interactions with the platform.

### Backend class structure
![](./images/backand-structure.jpg)

### Endpoints
* Logged user
  * token endpoints
    * [POST] `/logout/{sessionToken}`
    * [POST] `/change-password/{sessionToken}` @body(ChangePasswordDTO)
  * tree endpoints
    * [GET] `/tree/{sessionToken}` - builds User's forest from database nodes
    * [POST] `/nodes` @body(NodeInstructionDTO) - add new node in User's forest
    * [PUT] `/nodes/{nodeId}` @body(NodeInstructionDTO) - update User's node
    * [DELETE] `/nodes/{nodeId}` @body(NodeInstructionDTO) - delete node and all it's children
* Un-logged user
  * token endpoints
    * [POST] `/login` @body(UserDTO)
    * [POST] `/registration` @body(UserDTO)
    * [GET] `/registration/{registartionToken}` - activation of new account
    * [POST] `/forgot-password` @body(ForgetPasswordDTO)
    * [GET] `/forgot-password/{resetToken}` - reset User's password; generation of random temporary password

### Structure of DTO classes
```java
public class UserDTO {
  private String username;
  private String password;
  private String email;
}

public class ChangePasswordDTO {
    private String currentPassword;
    private String newPassword;
}

public class ForgetPasswordDTO {
  private String email;
}
```

[back to README](./../README.md)