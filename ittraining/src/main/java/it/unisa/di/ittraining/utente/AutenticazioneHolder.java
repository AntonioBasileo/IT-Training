package it.unisa.di.ittraining.utente;

public class AutenticazioneHolder {

  private static ThreadLocal<String> utenteThreadLocal = new ThreadLocal<>();

  /**
  * Permette di aggiungere l'username dell'utente autenticato in sessione al fine di renderlo
  * visibile a tutti i livelli.
  * 
  * @param username Stringa che rappresenta l'username dell'utente autenticato nel sistema
  */
  public static void setUtente(String username) {

    if (username != null) {

      utenteThreadLocal.set(username);

    } else {

      utenteThreadLocal.remove();
    }
  }

  /**
  * Permette di ottenere l'username dell'utente autenticato nel sistema.
  * 
  * @return La stringa che rappresenta l'username dell'utente autenticato nel sistema,
  *         <b>null</b> se non vi è alcun utente in sessione
  */
  public static String getUtente() {
    return utenteThreadLocal.get();
  }
}