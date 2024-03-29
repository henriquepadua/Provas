import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        /* Iniciando consumo de api */

        //fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.themoviedb.org/3/movie/550?api_key=49b7508a20e2f8302bffa56241a8601c";
        URI endereço = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //extrair dados que interessam {titulo,poster,classificação}
        var parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());
        //exibir e manipular os dados 
    }
}
