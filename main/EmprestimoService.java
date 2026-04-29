public class EmprestimoService {
    public static void emprestarLivro(String livroId, String usuarioId) {
        EmprestimoDAO.emprestar(livroId, usuarioId);
    }

    public static void devolverLivro(String livroId) {
        EmprestimoDAO.devolver(livroId);
    }
}