# Pesquisa e Ordenação

### Conteúdo: 
Organização Indexada em Árvore B+ (B+-Tree):
 - A variação mais comum em sistemas de banco de dados.
 - Diferencial principal: Os dados reais (ou ponteiros para eles) são armazenados apenas nos nós folha, e todos os nós folha são ligados entre si.
 - Vantagem: Permite buscas sequenciais mais rápidas (percorrendo as folhas) e um número maior de chaves em nós internos.
