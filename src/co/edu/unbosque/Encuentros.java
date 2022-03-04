package co.edu.unbosque;

public class Encuentros {
	private int consecutivo;
	private String equipoLocal;
	private String  equipoVisitante;
	private String fecha;
	private String deporte;
	
	public Encuentros(int consecutivo,String equipoL, String equipoV, String fecha, String deporte) {
		this.consecutivo=consecutivo;
		this.equipoLocal=equipoL;
		this.equipoVisitante=equipoV;
		this.fecha=fecha;
		this.deporte=deporte;		
	}

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}
	
	
	

}
