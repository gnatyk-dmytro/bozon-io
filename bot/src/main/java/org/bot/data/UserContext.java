package org.bot.data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@Builder
@Entity
@Table( name = "USERDATA")
public class UserContext {

    @Id
    @NotNull
    @Column( name = "_id")
    private Integer userId;

    @Size( max = 32 )
    @Column( name = "_name")
    private String userName;

    @NotNull
    @Size( max = 32 )
    @Column( name = "_email")
    private String userEmail;

    @NotNull
    @Column( name = "_status")
    private boolean userStatus;

    public UserContext(Integer userId, String userName, String userEmail, boolean userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
    }
}
