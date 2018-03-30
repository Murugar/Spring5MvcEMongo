package com.iqmsoft;

import com.iqmsoft.model.Address;
import com.iqmsoft.model.User;
import com.iqmsoft.repository.RepositoryMongo;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;
import org.junit.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestEmbeddedMongo {

    private static final String LOCALHOST = "127.0.0.1";
    private static final String DB_NAME = "test";
    private static final int MONGO_TEST_PORT = 27017;

    private RepositoryMongo reposytoryMongoImplements;
    private MongoTemplate mongoTemplate;

    private static MongodProcess mongoProcess;
    private static Mongo mongo;

    @BeforeClass
    public static void iniciarMongoDb() throws IOException {
        RuntimeConfig runtimeConfig = new RuntimeConfig();
        runtimeConfig.setExecutableNaming(new UserTempNaming());
        MongodStarter mongodStarter = MongodStarter.getInstance(runtimeConfig);

        MongodExecutable mongodExecutable = mongodStarter.prepare(new MongodConfig(Version.V2_2_0, MONGO_TEST_PORT, false));
        mongoProcess = mongodExecutable.start();

        mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);
        mongo.getDB(DB_NAME);
    }

    @AfterClass
    public static void shutdownDB() throws InterruptedException {
        mongo.close();
        mongoProcess.stop();
    }

    @Before
    public void setUp() throws Exception {
        reposytoryMongoImplements = new RepositoryMongo();
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        reposytoryMongoImplements.setMongoOps(mongoTemplate);
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(User.class);
    }

    public List<User> criarPessoas(int quantidadeUsuarios, int quantidadeEnderecos) {
        List<User> usuarios = new ArrayList<User>();

        for(int i = 1; i <= quantidadeUsuarios; i++) {
            User usuario = new User();
            List<Address> enderecos = new ArrayList<Address>();

            for(int j = 1; j <= quantidadeEnderecos; j++) {
                Address endereco = new Address();
                endereco.setId(j).setStreet("R. Teste" + j).setNumero(j).setNeighborhood("B. Teste" + j)
                .setCity("C. Teste" + j).setState("E. Teste" + j);
                enderecos.add(endereco);
            }

            usuario.setId(i).setNome("teste" + i).setIdade(20 + i).setEnderecos(enderecos);
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public void salvarUsuarios(int quantidadeUsuarios, int quantidadeEnderecos) {
        List<User> listaUsuarios = criarPessoas(quantidadeUsuarios, quantidadeEnderecos);

        for (User usuario : listaUsuarios) {
            for(Address endereco : usuario.getEnderecos()) {
                reposytoryMongoImplements.save(endereco);
            }
            reposytoryMongoImplements.save(usuario);
        }
    }

    @Test
    public void testSaveInMongo() {
        int quantidadeUsuarios = 1;
        int quantidadeEnderecos = 1;

        salvarUsuarios(quantidadeUsuarios, quantidadeEnderecos);

        int quantidadeUsuariosSalvos = mongoTemplate.findAll(User.class).size();

        Assert.assertEquals("numero de quantidade de usuarios salvos no mongo --> " + quantidadeUsuariosSalvos, quantidadeUsuariosSalvos, quantidadeUsuarios);
    }

    @Test
    public void testEncontrarPorId() {
        int quantidadeUsuarios = 5;
        int quantidadeEnderecos = 5;
        String nomeUsuario = "teste3";

        salvarUsuarios(quantidadeUsuarios, quantidadeEnderecos);

        List<User> listaUsuariosSalvos = reposytoryMongoImplements.encontrarPorNome(nomeUsuario);

        Assert.assertEquals("A quantidade de usuarios salvos com o nome de " + nomeUsuario + " = " + listaUsuariosSalvos.size(),
                listaUsuariosSalvos != null && listaUsuariosSalvos.size() > 0, true);

    }

    @Test
    public void testeEncontrarPorId() {
        int quantidadeUsuarios = 5;
        int quantidadeEnderecos = 5;
        int idEncontar = 1;

        salvarUsuarios(quantidadeUsuarios, quantidadeEnderecos);

        User UsuarioSalvo = (User) reposytoryMongoImplements.encontrarPorId(idEncontar, User.class);

        Assert.assertNotNull(UsuarioSalvo);
    }
}
