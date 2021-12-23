package studio.cyapp.spring.mafia.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "mafia.root")
public class RootElement {

    private String PK;  // Partition Key
    private String SK;  // Sort Key
    private String RK;  // Reference Key
    private String fcmToken;    // To send personalized msg using FCM
    private String nickname;
    private String role;

    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        if (PK == null) PK = UUID.randomUUID().toString();
        return PK;
    }

    public void setPK(String pk) {
        this.PK = pk;
    }

    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        if (SK == null) SK = UUID.randomUUID().toString();
        return SK;

    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    @DynamoDBAttribute
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @DynamoDBAttribute
    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    @DynamoDBAttribute
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @DynamoDBAttribute
    public String getRK() {
        return RK;
    }

    public void setRK(String RK) {
        this.RK = RK;
    }

}
