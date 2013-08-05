

class geraPHP():
	def __init__(self,tabela):
		self.campos = []
		self.tabela = tabela

	def addCampo(self,campo="",tipo="",tamanho=0):
		self.campos.append((campo,tipo,tamanho))

	def addRelacao(self,tblRef,tbl):
		

	def gerar():
		php = '''
		<%php 
		$tabela = "'''tabela'''";
		'''
