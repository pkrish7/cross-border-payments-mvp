@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("User service is up and running!");
    }
}