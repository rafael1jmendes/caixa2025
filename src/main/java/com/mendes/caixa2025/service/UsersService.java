import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mendes.caixa2025.model.Users;
import com.mendes.caixa2025.repository.UsuarioRepository;
import org.springframework.dao.DataAccessException;

@Service
@RequiredArgsConstructor // Gera um construtor com todos os campos finais
public class UsersService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void criarUsuario(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username não pode ser nulo ou vazio.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password não pode ser nulo ou vazio.");
        }

        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username já está em uso.");
        }

        try {
            Users usuario = new Users();
            usuario.setUsername(username);
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setEnabled(true);
            usuarioRepository.save(usuario);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao salvar o usuário no banco de dados.", e);
        }
    }
}