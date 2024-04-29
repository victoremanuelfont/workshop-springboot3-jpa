package com.educandoweb.course.entities.enums;

/* Para que não haja engano quanto ao valor de cada variável dentro do OrderStatus, atribui-se o valor manualmente (1)..(2).
 * No entanto, para que seja aceito, precisa-se de novas emplementações, referentes aos metodos a seguir: 
 * 
 */

public enum OrderStatus {

	WAITING_PAYMENT(1), PAID(2), SHIPPED(3), DELIVERED(4), CANCELED(5);

	private int code;

	private OrderStatus(int code) { // referente aos numeros atruibuidos dentro do OrderStatus
		this.code = code;
	}

	public int getCode() { // Para ficar disponível para o resto do código e fazer operações se necessário
		return code;
	}

	/*
	 * método para converter um valor numérico para um tipo enumerado: é static pois
	 * irá funcionar sem precisar instanciar. Vai ser passado um codigo inteiro (int
	 * code) e será retornado um OrderStatus. Exemplo: se passo o código 1, será
	 * retornado WAITING_PAYMENT.
	 * 
	 * 
	 */
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) { // PERCORRE TODOS OS VALORES DE ORDERSTATUS
			if (value.getCode() == code) {
				return value;
			}
		} /*
			 * Se depois de percorrer todo o for, e o código que eu quero não estiver
			 * disponível na lista de OrderStatus , será lançado uma exceção
			 */
		
		

		throw new IllegalArgumentException("Invalid OrderStatus code");
	}

}
