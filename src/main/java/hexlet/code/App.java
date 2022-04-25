package hexlet.code;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class App {
    private static final String DEV = "development";

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "5000");
        return Integer.parseInt(port);
    }

    private static void addRoutes(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello"));
    }

    public static boolean isDevEnv() {
        return System.getenv()
                .getOrDefault("ENV_TYPE", DEV)
                .equals(DEV);
    }

    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
            if (isDevEnv()) {
                config.enableDevLogging();
            }
                //config.enableWebjars();
        });

        app.before(ctx -> {
            ctx.attribute("ctx", ctx);
        });

        addRoutes(app);
        return app;
    }
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
}
