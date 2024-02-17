package fun.icpc.iris.sharedkernel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderTypeEnum {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non-Binary"),
    GENDERFLUID_GENDER_DIVERSE("Genderfluid/Gender Diverse"),
    AGENDER("Agender"),
    TRANSGENDER("Transgender"),
    INTERSEX("Intersex"),
    QUESTIONING("Questioning"),
    OTHER("Other"),
    ;

    private final String display;

}
