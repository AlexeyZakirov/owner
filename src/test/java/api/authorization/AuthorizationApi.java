package api.authorization;

import io.restassured.http.ContentType;
import api.models.login.LoginModel;
import api.models.login.ResponseLoginModel;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static ResponseLoginModel getAuthorization() {
        LoginModel login = new LoginModel("tinwhip1", "Password@123!");
        return given()
                .body(login)
                .contentType(ContentType.JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().as(ResponseLoginModel.class);
    }
}
