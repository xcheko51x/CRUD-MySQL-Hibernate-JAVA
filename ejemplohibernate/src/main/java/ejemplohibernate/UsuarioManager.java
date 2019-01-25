package ejemplohibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UsuarioManager {
	protected SessionFactory sessionFactory;
	
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch(Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	protected void exit() {
		sessionFactory.close();
	}
	
	protected void crear() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Yovanna");
		usuario.setTelefono("23424242");
		usuario.setEmail("yovana@hola.es");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(usuario);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	protected void leer(int idUsuario) {
		Session session = sessionFactory.openSession();
		
		Usuario usuario = session.get(Usuario.class, idUsuario);
		
		System.out.println("ID: " + usuario.getIdUsuario());
		System.out.println("Nombre: " + usuario.getNombre());
		System.out.println("Telefono: " + usuario.getTelefono());
		System.out.println("Email: " + usuario.getEmail());
		
		session.close();
	}
	
	protected void leerTodo() {
		Session session = sessionFactory.openSession();
		
		Criteria crit = session.createCriteria(Usuario.class);
		crit.setMaxResults(100);
		List<Usuario> usuarios = crit.list();
		
		for(int i = 0 ; i <usuarios.size() ; i++) {
			System.out.println("ID: " + usuarios.get(i).getIdUsuario());
			System.out.println("Nombre: " + usuarios.get(i).getNombre());
			System.out.println("Telefono: " + usuarios.get(i).getTelefono());
			System.out.println("Email: " + usuarios.get(i).getEmail());
			System.out.println();
		}
		
		session.close();
	}
	
	protected void actualiza(int idUsuario) {
		Usuario usuario = new Usuario();
		
		usuario.setIdUsuario(idUsuario);
		usuario.setNombre("Juan");
		usuario.setTelefono("23424245");
		usuario.setEmail("juan@hola.es");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(usuario);
		
		session.getTransaction().commit();
		
		session.close();
	
	}
	
	protected void borrar(int idUsuario) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(idUsuario);
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.delete(usuario);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public static void main(String[]args) {
		UsuarioManager usuarioManager = new UsuarioManager();
		usuarioManager.setup();
		
		//usuarioManager.crear();
		//usuarioManager.leer(13);
		//usuarioManager.leerTodo();
		//usuarioManager.actualiza(13);
		usuarioManager.borrar(13);
		
	}
}
