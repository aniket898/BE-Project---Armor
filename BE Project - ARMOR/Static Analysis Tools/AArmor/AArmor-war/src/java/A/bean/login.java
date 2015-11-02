/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package A.bean;

public class login 
{
    String usern;
    String pass;
    String email;
    public boolean valid;

   public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public login(String usern, String pass, String email)
			 {
		super();
		this.usern = usern;
		this.pass = pass;
		this.email = email;
		
	}

    public login() {
        usern="";
        pass="";
        email="";
       


    }
}
