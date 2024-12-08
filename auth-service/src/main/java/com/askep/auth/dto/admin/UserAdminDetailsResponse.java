package com.askep.auth.dto.admin;

import com.askep.auth.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserAdminDetailsResponse extends UserAdminResponse {

    List<RoleEntity> roles;

}
