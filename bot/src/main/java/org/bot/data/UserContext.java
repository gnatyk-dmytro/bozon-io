package org.bot.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "USERDATA" )
public class UserContext {

    @Id
    @NotNull
    @Column( name = "_id")
    private Long userId;

    @Size( max = 32 )
    @Column( name = "_name")
    private String userName;

    @NotNull
    @Size( max = 32 )
    @Column( name = "_email")
    private String userEmail;

    @NotNull
    @Column( name = "_num" )
    private Integer userNum;
}
