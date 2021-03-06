<xml>
	<loja>314276853</loja>
	<nat_operacao>Almoços, Jantares, Refeições e Pizzas</nat_operacao>
	<pedido>
		<items>
			<#list pedido.itens as item>
			<item>
				<descricao>${item.itemDoCardapio.nome}</descricao>
				<un>un</un>
				<codigo>004</codigo>
				<qtde>${item.quantidade}</qtde>
				<vlr_unit>${(item.itemDoCardapio.precoEfetivo)?string["#,##0.00"]}</vlr_unit>
				<tipo>P</tipo>
				<class_fiscal>21069090</class_fiscal>
			</item>
			</#list>
		</items>
	</pedido>
	<cliente>
		<nome>${pedido.entrega.cliente.nome}</nome>
		<tipoPessoa>F</tipoPessoa>
		<contribuinte>9</contribuinte>
		<cpf_cnpj>${pedido.entrega.cliente.cpf}</cpf_cnpj>
		<email>${pedido.entrega.cliente.email}</email>
		<endereco>${pedido.entrega.endereco}</endereco>
		<complemento>${(pedido.entrega.complemento)!"-"}</numero>
		<cep>${pedido.entrega.cep}</cep>
	</cliente>
</xml>