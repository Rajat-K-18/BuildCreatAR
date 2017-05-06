import org.gradle.api.Project;
import org.gradle.api.Plugin;
import com.sample.gradle.SamplePluginExtension;

public class ApplyPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        target.getExtensions().create("samplePlugin",
                ApplyPluginExtension.class);    }
}