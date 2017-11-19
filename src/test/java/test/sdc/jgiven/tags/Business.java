package test.sdc.jgiven.tags;

import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * JGiven tag.
 */
@IsTag(style = "background-color: orange",
        description = "Business logic")
@Retention(RetentionPolicy.RUNTIME)
public @interface Business {
}