package com.belloda.reactive.webclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticalPartyResponse {    

    public int idProcesoElectoral;
    public int idTipoEleccion;
    public String strRegion;
    public String strProvincia;
    public String strDistrito;
    public String strUbigeo;
    public int idExpediente;
    public int nuCorrelativo;
    public String strCodExpediente;
    public int idOrganizacionPolitica;
    public String strOrganizacionPolitica;
    public int idSolicitudLista;
    public String strEstadoLista;
    public int idJuradoElectoral;
    public String strJuradoElectoral;
    public String strTipoOrganizacion;
    public int intCandHombres;
    public int intCandMujeres;
    public int intDetalle;
    public String strRutaArchivo;
    public String strFGTieneArchivo;
    public int idPlanGobierno;
    public String strAmbiente;
    public String strAmbienteSIJE;
    public String strCarpeta;
    public String strDistritoElec;
}
