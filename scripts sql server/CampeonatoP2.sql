use Campeonato


----------------------------TRIGGERS TIMES-----------------------------------

CREATE TRIGGER t_blockDelTimes ON Times
FOR DELETE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido excluir registros de times', 16, 1)
END

Delete from Times

CREATE TRIGGER t_blockInsertTimes ON Times
FOR INSERT
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido inserir registros de times', 16, 1)
END

insert into Times Values(17,'aaaa','aaaaa','sssss')

CREATE TRIGGER t_blockUpdateTimes ON Times
FOR UPDATE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido atualizar registros de times', 16, 1)
END

select * from Times
update Times set NomeTime = 'aaaa' where CodigoTime = 1




----------------------------TRIGGERS GRUPOS-----------------------------------




CREATE TRIGGER t_blockDelGrupos ON Grupos
FOR DELETE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido excluir registros de Grupos', 16, 1)
END

Delete from Grupos

CREATE TRIGGER t_blockInsertGrupos ON Grupos
FOR INSERT
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido inserir registros de Grupos', 16, 1)
END

select * from Grupos
insert into Grupos Values('A',4)

CREATE TRIGGER t_blockUpdateGrupos ON Grupos
FOR UPDATE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido atualizar registros de Grupos', 16, 1)
END

select * from Grupos
update Grupos set Grupo = 'C' where CodigoTime = 2


----------------------------TRIGGERS JOGOS-----------------------------------

CREATE Delete TRIGGER t_blockDelJogos ON Jogos
FOR DELETE
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido excluir registros de Jogos', 16, 1)
END

Delete from Jogos

CREATE TRIGGER t_blockInsertJogos ON Jogos
FOR INSERT
AS
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('Não é permitido inserir registros de Jogos', 16, 1)
END

select * from Jogos
insert into Jogos values(1,1,0,0,GETDATE())

---------------------- Procedure para inserir gols -------------------------

CREATE PROCEDURE sp_insereGolsJogos
AS
	create table #tabela 
	(indice int null , TimeA int null,TimeB int null)
	declare @lenTabela int, @x int,@timeA int, @timeB int,@cont int
	set @cont = 1
	while @cont <= 15
	BEGIN
		Delete from #tabela
		insert into #tabela (indice,TimeA,TimeB) select ROW_NUMBER() OVER(ORDER BY CodigoTimeA) as indice,CodigoTimeA,CodigoTimeB as numTime from Jogos where CodigoTimeA = @cont
		set @lenTabela = (select count(*) from #tabela)
		set @x = 1
		while @x <= @lenTabela
		BEGIN
			set @timeA = (select TimeA from #tabela where indice = @x)
			set @timeB = (select TimeB from #tabela where indice = @x)
			update Jogos set GolsTimeA = CAST(RAND()*(5) as int),GolsTimeB = CAST(RAND()*(5) as int) where CodigoTimeA = @timeA and CodigoTimeB = @timeB
			set @x = @x + 1;
		END
		set @cont = @cont + 1
	END

--------------------------------- Funcao Grupos ------------------------------------

CREATE FUNCTION fn_Grupos(@nomeG varchar(4))
RETURNS @grupo TABLE(
nome_time varchar(50),
num_jogos int,
vitorias int,
empates int,
derrotas int,
gols_marcados int,
gols_sofridos int,
saldo_gols int,
pontos int
)
AS
BEGIN
	declare @contGrup int,@time int,@lenJogos int,@contJogos int,@vitorias int, @empates int, @derrotas int, 
	@gols_marcados int, @gols_sofridos int, @saldo_gols int,@pontos int,@golsA int, @golsB int,@nomeTime varchar(50),@numJogos int
	declare  @tabelaGrupo table
	(indice int null , Time int null)
	declare  @tabelaJogos table
	(indice int null , TimeA int null,TimeB int null,GolsA int,GolsB int)
	--Criando tabela dos times do Grupo x
	insert into @tabelaGrupo (indice,Time) select ROW_NUMBER() OVER(ORDER BY CodigoTime), CodigoTime from Grupos where Grupo = @nomeG;
	set @contGrup = 1
	while @contGrup <= 4
	BEGIN
		set @time = (select Time from @tabelaGrupo where indice = @contGrup)
		insert into @tabelaJogos (indice,TimeA,TimeB,GolsA ,GolsB) select ROW_NUMBER() OVER(ORDER BY CodigoTimeA) as indice,CodigoTimeA,CodigoTimeB,GolsTimeA,GolsTimeB from Jogos where CodigoTimeA = @time or CodigoTimeB = @time
		set @lenJogos = (select COUNT(*) from @tabelaJogos)
		set @contJogos = 1
		set @vitorias = 0
		set @empates = 0
		set @derrotas = 0
		set @gols_marcados = 0
		set @gols_sofridos = 0
		set @saldo_gols = 0
		set @pontos = 0
		set @numJogos = 0
		while @contJogos <= @lenJogos
		BEGIN
			
			if (select TimeA from @tabelaJogos where indice = @contJogos) = @time
			BEGIN
				
				set @golsA = (select GolsA from @tabelaJogos where indice = @contJogos)
				set @golsB = (select GolsB from @tabelaJogos where indice = @contJogos)
				
			END

			if (select TimeB from @tabelaJogos where indice = @contJogos) = @time
			BEGIN
				
				set @golsA = (select GolsB from @tabelaJogos where indice = @contJogos)
				set @golsB = (select GolsA from @tabelaJogos where indice = @contJogos)
				
			END
			
			if NOT(@golsA is null or @golsB is null)
			BEGIN
				set @gols_marcados = @golsA + @gols_marcados
				set @gols_sofridos = @golsB + @gols_sofridos

				if (@golsA >= @golsB)
				BEGIN
					if (@golsA > @golsB)
					BEGIN
						set @vitorias = @vitorias + 1
						set @pontos = @pontos + 3
					END
					ELSE
					BEGIN
						set @empates = @empates + 1
						set @pontos = @pontos + 1
					END
				END
				ELSE
				BEGIN
					set @derrotas = @derrotas + 1
				END
				set @numJogos = @numJogos + 1
			END
			set @contJogos = @contJogos + 1
		END
		Delete from @tabelaJogos
		set @nomeTime = (select NomeTime from Times where CodigoTime = @time)
		insert into @grupo values (@nomeTime,@numJogos,@vitorias,@empates,@derrotas,
		@gols_marcados,@gols_sofridos,(@gols_marcados-@gols_sofridos),@pontos)		
		set @contGrup = @contGrup + 1
	END	
	RETURN
END

select * from fn_Grupos('B') Order by pontos DESC, vitorias, gols_marcados, saldo_gols

--------------------------------- Funcao Campeonato ---------------------------------

select * from Jogos

select * from Grupos

CREATE FUNCTION fn_Campeonato()
RETURNS @grupo TABLE(
nome_time varchar(50),
num_jogos int,
vitorias int,
empates int,
derrotas int,
gols_marcados int,
gols_sofridos int,
saldo_gols int,
pontos int
)
AS
BEGIN
	declare @vetorGrupos varchar(5), @cont int
	set @vetorGrupos = 'ABCD'
	set @cont = 1
	while @cont <= 4
	BEGIN
		insert into @grupo (nome_time,num_jogos, vitorias, empates, derrotas, gols_marcados, gols_sofridos, saldo_gols,pontos) select * from fn_Grupos(SUBSTRING(@vetorGrupos, @cont, 1))
		set @cont = @cont + 1
	END
	return
END


select * from fn_Campeonato() Order by pontos DESC, vitorias, gols_marcados, saldo_gols

--------------------------------- Funcao Quartas de final ---------------------------------

CREATE FUNCTION fn_QuartasDeFinal()
RETURNS @jogos TABLE(
NomeTimeA varchar(50),
NomeTimeB varchar(50)
)
AS
BEGIN
	declare @vetorGrupos varchar(5), @cont int, @NomeTimeA varchar(50), @NomeTimeB varchar(50)
	declare  @tabelaGrupo table
	(indice int null , nomeTime varchar(50) null)
	set @vetorGrupos = 'ABCD'
	set @cont = 1
	while @cont <= 4
	BEGIN
		Delete from @tabelaGrupo
		insert into @tabelaGrupo (indice,nomeTime) select top 2 ROW_NUMBER() over (Order by pontos DESC, vitorias, gols_marcados, saldo_gols) as id,nome_time from fn_Grupos(SUBSTRING(@vetorGrupos, @cont, 1)) Order by pontos DESC, vitorias, gols_marcados, saldo_gols
		set @NomeTimeA = (select nomeTime from @tabelaGrupo where indice = 1)
		set @NomeTimeB = (select nomeTime from @tabelaGrupo where indice = 2)
		insert into @jogos values (@NomeTimeA,@NomeTimeB)
		set @cont = @cont + 1
	END
	return
END

select * from fn_QuartasDeFinal()

select * from Jogos

use Campeonato

select (Select NomeTime from Times where CodigoTime = CodigoTimeA) As TimeA, CodigoTimeA, GolsTimeA ,(Select NomeTime from Times where CodigoTime = CodigoTimeB) As TimeB ,CodigoTimeB, GolsTimeA, Data_Jogo from Jogos ORDER BY Data_Jogo