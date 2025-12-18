package manajemen.kas.model;

/**
 *
 * @author ASPIRESS
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String namaLengkap;
    private String jabatan;

    public User() {
    }

    public User(Integer id, String username, String password, String namaLengkap, String jabatan) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.jabatan = jabatan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

}
