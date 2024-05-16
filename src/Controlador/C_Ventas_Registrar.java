
package Controlador;

import DAO.DAO_Venta;
import Modelo.M_Ventas_Registrar;
import Procesos.ProcesosFrmVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.V_Ventas_Registrar;
import java.util.Date;
import javax.swing.JOptionPane;

public class C_Ventas_Registrar implements ActionListener {

    V_Ventas_Registrar vista;  
    DAO_Venta crud;
    M_Ventas_Registrar tran;
    
public C_Ventas_Registrar(V_Ventas_Registrar if2){
        vista = if2;
        vista.btnRegistrar_Venta.addActionListener(this);
        crud = new DAO_Venta();
        crud.MostrarVentas(vista.tblResumen_R_Venta);
    }//fin del constructor


public void registrarVenta() {
    // Extraer datos de los componentes de la vista
    int IDVenta = Integer.parseInt(vista.txtIDVenta_R_Venta.getText());
    Date fechaVenta = vista.datecFecha_R_Venta.getDate();
    String productoVenta = vista.cbxAreaEmpleado_G_Transaccion.getSelectedItem().toString();
    String clienteVenta = vista.txtCliente_R_Venta.getText();
    int cantidadVenta = Integer.parseInt(vista.txtCantidad_R_Venta.getText());
    String detalleVenta = vista.txaDetalle_R_Venta.getText();

    double precioUnitario = Double.parseDouble(vista.txtPrecioU_R_Venta.getText());
    double totalventa = cantidadVenta * precioUnitario;
    
    // Crear objeto de venta
    //M_Ventas_Registrar venta = new M_Ventas_Registrar(IDVenta, fechaVenta, productoVenta, clienteVenta, cantidadVenta, detalleVenta, precioUnitario, totalventa);
    Object[] data = new Object[]{IDVenta,fechaVenta, productoVenta, clienteVenta, cantidadVenta, detalleVenta, precioUnitario, totalventa};
    vista.getTblResumen_R_Venta().addRow(data); 
    JOptionPane.showMessageDialog(null, "Transacción registrada correctamente");
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar_Venta) {
            tran = ProcesosFrmVentas.LeerDatos(vista);
            crud = new DAO_Venta();
            crud.InsertarVenta(tran);
        }
}
}
    
