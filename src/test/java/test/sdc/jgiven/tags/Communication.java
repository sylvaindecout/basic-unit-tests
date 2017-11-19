package test.sdc.jgiven.tags;

import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * JGiven tag.
 */
@IsTag(style = "background-color: blue",
        description = "Communication between services")
@Retention(RetentionPolicy.RUNTIME)
public @interface Communication {
}