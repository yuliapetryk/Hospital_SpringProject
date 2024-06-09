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
        return  Unirest.post("https://dev-p28zkraycjo5kb7x.eu.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"qfEnDGWMic6hN9Dulvqzei9oX3wEuUko\",\"client_secret\":\"oc8HjJ7SSHnaY35HROw9Zp13_hUaGq0i7ZSgds_v3NpuKkszqC4rEjsR76SI2l1u\",\"audience\":\"https://dev-p28zkraycjo5kb7x.eu.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
                .asString();

    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}