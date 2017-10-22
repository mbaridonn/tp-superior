package model;

import javax.swing.table.DefaultTableModel;

public interface MetodoMinimosCuadrados {
	
	public void generarCalculos(DefaultTableModel tableModel);
	public String resolverSistemaEcuaciones(DefaultTableModel tableModel);

}
