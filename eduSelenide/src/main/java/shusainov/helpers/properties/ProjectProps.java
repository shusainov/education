package shusainov.helpers.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:project.properties"})

public interface ProjectProps extends Config {
    @Key("default.timeout")
    int defaultTimeout();

}
