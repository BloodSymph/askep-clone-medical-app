package com.askep.medpersonal.dto.file;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    @NotBlank(message = "File path field shod not be empty!")
    @NotNull(message = "File path field shod not be contains null value!")
    private String encodedFile;

}
