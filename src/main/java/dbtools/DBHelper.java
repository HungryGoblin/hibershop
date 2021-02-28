package dbtools;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Data
public class DBHelper {

    private static Path scriptDir;
    private static SessionFactory factory;
    private static Session session;

    static {
        try {
            scriptDir = Paths.get(ClassLoader.getSystemResource("sql").toURI());
            factory = new Configuration()
                    .configure("configs/hibernate_cfg.xml")
                    .buildSessionFactory();
            session = factory.getCurrentSession();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void executeSQL (String script) throws IOException {
        Path scriptPath = DBHelper.scriptDir.resolve(script);
        String sql = Files.lines(scriptPath).collect(Collectors.joining(" "));
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
    }

    public static void createTables () throws IOException {
        executeSQL("create_tables.sql");
    }

    public static void createData () throws IOException {
        executeSQL("create_data.sql");
    }

    public static void close () {
        factory.close();
        if (session != null) {
            session.close();
        }
    }

    public static void main(String[] args) {
        try {
            createTables();
            createData();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

}
