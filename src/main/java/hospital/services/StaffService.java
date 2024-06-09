package hospital.services;


import hospital.entities.Staff;
import hospital.repository.StaffRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public HttpResponse<String> getAllStaff() throws UnirestException {
        String clientId = System.getenv("AUTH0_CLIENT_ID");
        String clientSecret = System.getenv("AUTH0_CLIENT_SECRET");
        String audience = System.getenv("AUTH0_AUDIENCE");
        
        String requestBody = String.format("{\"client_id\":\"%s\",\"client_secret\":\"%s\",\"audience\":\"%s\",\"grant_type\":\"client_credentials\"}",
                clientId, clientSecret, audience);

        return Unirest.post("https://dev-p28zkraycjo5kb7x.eu.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body(requestBody)
                .asString();
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}