package br.com.vedoy.dao;

import br.com.vedoy.controle.ControleLogin;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class DAOGenerico<T> implements Serializable {

    // lista paginada
    protected List<T> listaObjetos;
    // lista com todos os objetos
    protected List<T> listaTodos;
    @PersistenceContext(unitName = "OSWebPU")
    protected EntityManager em;
    protected Class classePersistente;
    protected String ordem = "";
    protected String filtro = "";
    protected Integer maximoObjetos = 0;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;
    private Integer user;
    private List<T> listaCliente;
    private List<T> listaTecnico;

    public DAOGenerico() {
    
    }

     public List<T> getListaObjetos() {
        String jpql = " from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0){
            if (ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e){}
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }            
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();        
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
    
    public List<T> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordem;
        return em.createQuery(jpql).getResultList();
    } 
    
    public void primeiro(){
        posicaoAtual = 0;
    }
    
    public void anterior(){
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0){
            posicaoAtual = 0;
        }
    }
    
    public void proximo(){
        if (posicaoAtual + maximoObjetos < totalObjetos){
            posicaoAtual += maximoObjetos;
        }
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0){
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }
    
   
   
  
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos){
            ate = totalObjetos;
        }
        return "User:  "+getUser();
    }
    
    public void persist(@Valid T obj) throws Exception {
        em.persist(obj);
    }

    public void merge(T obj) throws Exception {
        em.merge(obj);
    }
    
    public void remove(T obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }

    public T getObjectById(Integer id) throws Exception {
        return (T) em.find(classePersistente, id);
    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public List<T> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<T> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<T> getListaTecnico() {
        return listaTecnico;
    }

    public void setListaTecnico(List<T> listaTecnico) {
        this.listaTecnico = listaTecnico;
    }
}
