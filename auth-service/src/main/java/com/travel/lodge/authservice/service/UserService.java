package com.travel.lodge.authservice.service;

import com.travel.lodge.authservice.dto.CreateResponse;
import com.travel.lodge.authservice.dto.User;
import com.travel.lodge.authservice.exception.UserExistsException;
import com.travel.lodge.authservice.util.Consts;
import com.travel.lodge.authservice.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final Keycloak keycloak;
    final EncryptionUtil encryptionUtil;


    public CreateResponse addUser(User user) {
        log.info(Consts.Logs.BRACKETS_3, Consts.Logs.ENTERED, Consts.Logs.ADD_USER, user.getEmail());
        var usersResource = keycloak.realm("hotel-realm").users();
        var passKey = encryptionUtil.decrypt(user.getPassword());
        var credentialRepresentation = createPasswordCredentials(passKey);

        var kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

        try (var response = usersResource.create(kcUser)) {
            if (response.getStatus() == 409) {
                throw new UserExistsException();
            } else if (response.getStatus() == 201) {
                return new CreateResponse(HttpStatus.CREATED.name(), Consts.ResponseMessages.CREATED_USER);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        return null;

    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}
