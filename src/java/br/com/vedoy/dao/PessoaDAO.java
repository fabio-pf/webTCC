
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Pessoas;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;


@Stateful
public class PessoaDAO<T> extends DAOGenerico<Pessoas> implements Serializable {

    public PessoaDAO(){
        super();        
        super.classePersistente = Pessoas.class;
        ordem = "nome";
    }
    
    public Usuarios localizaPorNomeUsuario(String usuario) {
        Pessoas obj = (Pessoas)em.createQuery("from Pessoas where usuario = :nome").
                setParameter("nome", usuario).getSingleResult();
        System.out.println("objeto " +obj);
        return obj;
    }
    
    @Override
    public Pessoas getObjectById(Integer id) throws Exception {
        Pessoas obj = (Pessoas) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
    @Override
    public List<Pessoas> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        // limpando o filtro contra injeção de SQL
        filtro = filtro.replaceAll("[';-]","");
        if (filtro.length() > 0){
            if (ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                }catch (Exception e){}
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by "+ordem;
        totalObjetos = em.createQuery("select nome from " + classePersistente.getSimpleName() +
                where + " order by " + ordem).getResultList().size();
        System.out.println("total de obj: "+totalObjetos + "JPQL " +jpql);
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    }    
    
}
