function hfsDatatablesLinguagem() {
	var linguagem = {
		decimal: ",",
		thousands: ".",
		emptyTable: "Nenhum dado disponível na tabela",
		info:           "Exibindo _START_ até _END_ total _TOTAL_ registros",
		infoEmpty:      "Nenhum registro encontrado.",
		infoFiltered:   "(filtrado total registros: _MAX_)",
		infoPostFix:    "",
		lengthMenu:     "Exibe _MENU_ registros",
		loadingRecords: "Carregando...",
		processing:     "Processando...",
		search:         "Persquisar:",
		zeroRecords:    "Nenhum registro encontrado.",
		paginate: {
			first:      "Primeiro",
			last:       "último",
			next:       "próximo",
			previous:   "anterior"
		},
		aria: {
			sortAscending:  ": ordenar a coluna ascendente",
			sortDescending: ": ordenar a coluna descendente"
		},			
		select: {
			rows: {
				_: "",
				0: "Clique num registro para selecionar"
			}
		}
	};
	
	return linguagem;	
}

function hfsDatatablesIniciar(nome) {
	
	var tabela = $('#tabela'+nome).DataTable({
		searching: true,
		ordering:  true,	
		dom: '<"top"lp>rt<"bottom"i><"clear">',
		pagingType: "full_numbers",
		select: {
			style: 'single'
		},
		lengthMenu: [10, 20, 50, 80, 100],
		language: hfsDatatablesLinguagem()
	});
	
	hfsDatatablesSelecionar(nome, tabela);
}

function hfsDatatablesIniciar(nome, colunaNaoOrdenavel) {
	
	var tabela = $('#tabela'+nome).DataTable({
		searching: true,
		ordering:  true,	
		dom: '<"top"lp>rt<"bottom"i><"clear">',
		pagingType: "full_numbers",
		select: {
			style: 'single'
		},
		lengthMenu: [10, 20, 50, 80, 100],
		language: hfsDatatablesLinguagem(),
		columnDefs: [ { orderable: false, targets: colunaNaoOrdenavel } ]
	});
	
	hfsDatatablesSelecionar(nome, tabela);
}

function hfsDatatablesSelecionar(nome, tabela) {
	
	$('#tabela'+nome+' thead th').each( function () {
		var title = $(this).text();
		$(this).html( title+'<br/><input style="width: 100%;" type="text" placeholder="Pesquisar '+title+'" />' );
	});
	
	tabela.on( 'select', function ( e, dt, type, indexes ) {
		var linhaDados = tabela.rows( indexes ).data().toArray();
		//alert( JSON.stringify( linhaDados ) );
	});		
		
	tabela.columns().every( function () {
		var that = this;
 
		$( 'input', this.header() ).on( 'keyup change', function () {
			if ( that.search() !== this.value ) {
				that.search( this.value ).draw();
			}
		});
	});
	
}

function hfsDatatablesOrdenar(nome) {
	var tabela = $('#tabela'+nome).DataTable();	
	tabela.column( '0:visible' ).order( 'asc' ).draw();
}

function hfsDatatablesEditar(nome) {
	$('#alert-messages').hide();

	var tabela = $('#tabela'+nome).DataTable();
	var linhaDados = tabela.rows( { selected: true } ).data().toArray();
	
	if (linhaDados.length > 0) {
		//return JSON.stringify( linhaDados );
		alert(JSON.stringify( linhaDados ));
		window.location.href='editar'+nome+'.html';
	} else {
		$('#alert-messages').show();
	}	
}

function hfsDatatablesExcluir(nome) {
	$('#alert-messages').hide();

	var tabela = $('#tabela'+nome).DataTable();
	var linhaDados = tabela.rows( { selected: true } ).data().toArray();
	
	if (linhaDados.length > 0) {
		bootbox.confirm({
			message: "Confirma exclusão?",
			buttons: {
				confirm: {
					label: 'Sim',
					className: 'btn-success'
				},
				cancel: {
					label: 'Não',
					className: 'btn-danger'
				}
			},
			callback: function (result) {
				if (result){
					var tabela = $('#tabela'+nome).DataTable();
					tabela.row('.selected').remove().draw( false );
				}
			}
		});
	} else {
		$('#alert-messages').show();
	}
}
