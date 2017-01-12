package br.com.ppmec.cbrmeca.control.models;

import java.util.List;
import java.util.Map;

import br.unb.ppmec.cbrmeca.db.model.FuncaoCaso;

public interface ICBREngine {
	List<FuncaoCaso> retrieveSimilar(FuncaoCaso fCaso, int idTipoCaso, String fDescriptor);
	Map<Integer, Integer> getSolutionsForCase(List<FuncaoCaso> similarCases);
}
