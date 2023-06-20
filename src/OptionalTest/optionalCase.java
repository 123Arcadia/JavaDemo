package OptionalTest;

import org.junit.Test;

import java.util.Optional;

public class optionalCase {

    @Test
    public void optional_create() {
        Girl girl = new Girl();
        Optional<Girl> optional = Optional.of(girl);
        System.out.println("optional = " + optional);
        //optional = Optional[Girl{name='null'}]

        Optional<Girl> optional_NUllable = Optional.ofNullable(girl);
        Girl orElse = optional_NUllable.orElse(new Girl("zzz"));
        System.out.println("orElse = " + orElse);
        //orElse = Girl{name='null'}
        System.out.println(girl == null); // false
    }


}
