function hfsPickListCriarOpcao(origem){
	var opcao = new Option(origem.text);
	opcao.defaultSelected = false;
	opcao.disabled = origem.disabled;
	opcao.form = origem.form;
	opcao.index = origem.index;
	opcao.label = origem.label;
	opcao.selected = false;
	//opcao.text = origem.text;
	opcao.value = origem.value;
	return opcao;
}

/*
function hfsPickListPesquisar(pLPesquisa, pLValor) {	
    for (i = pLValor.options.length-1; i >= 0; i--) {
		var texto = pLValor.options[i].text;
		if (pLPesquisa.value.length > 0) {
    	   	pLValor.options[i].selected = (texto.search(eval("/" + pLPesquisa.value + "/i"))!=-1);
		} else {
			pLValor.options[i].selected = false;
		}
    }
}
*/
	
function hfsPickListIniciar(nome){
	var pLPesquisa1 = document.getElementById("pickListPesquisa1_"+nome);
	var pLPesquisa2 = document.getElementById("pickListPesquisa2_"+nome);
	
	var pLValor1 = document.getElementById("pickListValor1_"+nome);
	var pLValor2 = document.getElementById("pickListValor2_"+nome);
		
	$("#pickListPesquisa1_"+nome).keyup(function(){
		var valor = $(this).val().toLowerCase();
		$("#pickListValor1_"+nome+" option").each(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
		});		
	});

	$("#pickListPesquisa2_"+nome).keyup(function(){
		var valor = $(this).val().toLowerCase();
			$("#pickListValor2_"+nome+" option").each(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
		});
	});

	$("#pickListBtnAdicionar_"+nome).click(function(){
		for (i = 0; i < pLValor1.options.length; i++) {
			if (pLValor1.options[i].selected){
				pLValor2.options.add(hfsPickListCriarOpcao(pLValor1.options[i]));
			}
		}
		for (i = pLValor1.options.length-1; i >= 0; i--) {
			if (pLValor1.options[i].selected){
				pLValor1.options.remove(i);
			}
		}
	});
	
	$("#pickListBtnTodosAdicionar_"+nome).click(function(){
		for (i = 0; i < pLValor1.options.length; i++) {
			pLValor2.options.add(hfsPickListCriarOpcao(pLValor1.options[i]));
		}
		for (i = pLValor1.options.length-1; i >= 0; i--) {
			pLValor1.options.remove(i);
		}
	});

	$("#pickListBtnRemover_"+nome).click(function(){
		for (i = 0; i < pLValor2.options.length; i++) {
			if (pLValor2.options[i].selected){
				pLValor1.options.add(hfsPickListCriarOpcao(pLValor2.options[i]));
			}
		}
		for (i = pLValor2.options.length-1; i >= 0; i--) {
			if (pLValor2.options[i].selected){
				pLValor2.options.remove(i);
			}
		}
	});
	
	$("#pickListBtnTodosRemover_"+nome).click(function(){
		for (i = 0; i < pLValor2.options.length; i++) {
			pLValor1.options.add(hfsPickListCriarOpcao(pLValor2.options[i]));
		}
		for (i = pLValor2.options.length-1; i >= 0; i--) {
			pLValor2.options.remove(i);
		}
	});
}
