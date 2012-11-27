	function startCadastrarPalavra()
	{
		$(function() {
			$("#enviaPalavra").click(
					function() {
						$.ajax({
							type : 'POST',
							url : 'CadastraPalavra',
							data : '{ "palavra" : "' + palavra.value
									+ '" , "dica" : "' + dica.value
									+ '" ,  "senha" : "' + senha.value + '" }',
							contentType : 'application/json; charset=utf-8',
							dataType : 'json',
							success : function(msg) {
								alert(msg.message);
								palavra.value = "";
								dica.value = "";
								senha.value = "";
								parent.$.fancybox.close();
							},
							failure : function(r, o) {
								senha.value = "";
								alert("falha: " + r.message);
							}
						});

					});

		});
	}

	function startForcaOnline()
	{

		$(function() {
			desenharCeu();
		});

		$(function() {
			$("#envia").click(function() {

				var canvas = document.querySelector("#forca");
				var context = canvas.getContext("2d");

				$.ajax({
					type : 'POST',
					url : 'JogarForca',
					data : '{ "letra" : "' + letra.value + '" }',
					contentType : 'application/json; charset=utf-8',
					dataType : 'json',
					success : function(msg) {
						//alert(msg.message);
						$("#desenho").html(msg.message);
						letra.value = "";
						if (msg.result == "0") {
							desenharCabeca(context);
						}
						if (msg.result == "1") {
							desenharTronco(context);
						}
						if (msg.result == "2") {
							desenharBracoEsquerdo(context);
						}
						if (msg.result == "3") {
							desenharBracoDireito(context);
						}
						if (msg.result == "4") {
							desenharPernaEsquerda(context);
						}
						if (msg.result == "5") {
							$("#desenho").html(msg.message);
							desenharPernaDireito(context);
						}
					}
				});

			});

		});

		$(function() {
			$("#inicia").click(function() {

				$.ajax({
					type : 'POST',
					url : 'IniciarForca',
					dataType : 'json',
					success : function(msg) {
						//alert(msg.message);
						$("#dica").html(msg.message);
						$("#letra").removeAttr("disabled");
						$("#envia").removeAttr("disabled");
						$("#inicia").attr("disabled", "disabled");
					}
				});

			});

		});

		$(function() {

			var canvas = document.querySelector("#forca");
			var context = canvas.getContext("2d");

			context.lineWidth = 1;
			context.strokeStyle = "green";

			context.save();
			context.lineJoin = 'bevel';

			desenharGramado(context);

			desenharSol(context);

			desenharForca(context);

		});
		$(document).ready(function() {
		
		$("#novaPalavra").fancybox({
			'width'				: '25%',
			'height'			: '40%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
		});
	}

	function desenharCeu()
	{
		var c = document.querySelector("#forca");
		var ctx = c.getContext("2d");

		var grd = ctx.createLinearGradient(0, 150, 0, 0);
		grd.addColorStop(1, "#00bfff");
		grd.addColorStop(0, "white");

		ctx.fillStyle = grd;
		ctx.fillRect(0, 0, 500, 200);
	}
	
	function desenharSol(context) {
		context.beginPath();
		context.strokeStyle = "yellow";
		context.arc(230, 20, 10, 0, 2 * Math.PI);
		context.fillStyle = 'yellow';
		context.fill();
		context.lineWidth = 5;
		context.strokeStyle = 'yellow';

		context.stroke();
		context.closePath();
		context.save();
		context.lineWidth = 1;
		context.strokeStyle = "green";
	}

	function desenharPernaEsquerda(context) {
		context.beginPath();
		context.moveTo(130, 100);
		context.lineTo(150, 120);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharPernaDireito(context) {
		context.beginPath();
		context.moveTo(130, 100);
		context.lineTo(110, 120);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharBracoDireito(context) {
		context.beginPath();
		context.moveTo(130, 60);
		context.lineTo(150, 80);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharBracoEsquerdo(context) {
		context.beginPath();
		context.moveTo(130, 60);
		context.lineTo(110, 80);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharTronco(context) {
		context.beginPath();
		context.moveTo(130, 60);
		context.lineTo(130, 100);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharCabeca(context) {
		context.beginPath();
		context.arc(130, 50, 10, 0, 2 * Math.PI);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharForca(context) {
		context.beginPath();
		context.strokeStyle = "#663300";
		context.moveTo(70, 139);
		context.lineTo(70, 20);
		context.lineTo(130, 20);
		context.lineTo(130, 40);
		context.stroke();
		context.closePath();
		context.save();
	}

	function desenharGramado(context) {
		context.beginPath();
		context.moveTo(0, 140);
		context.lineTo(300, 140);
		context.stroke();
		context.closePath();
		context.save();
	}