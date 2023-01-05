/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package samplepluginproject;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

/**
 * A simple 'hello world' plugin.
 */
public class SamplePluginProjectPlugin implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
        MyExtension myExtension = project.getExtensions().create("MyExtension", MyExtension.class);
        project.getTasks().register("greeting", task -> {
            task.doLast(s -> {
                System.out.println("Hello from plugin 'samplepluginproject.greeting' "+myExtension.getValue());
            });
        });
    }
}