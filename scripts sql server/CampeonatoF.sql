create database Campeonato
go
use Campeonato

create table Times(
CodigoTime int,
NomeTime varchar(50),
Cidade varchar(50),
Estadio varchar(50)
primary key (CodigoTime))

create table Grupos(
Grupo char(2) check (Grupo = 'A' or Grupo = 'B' or Grupo = 'C' or Grupo = 'D'),
CodigoTime int
primary key (Grupo,CodigoTime),
foreign key (CodigoTime) references Times(CodigoTime))

drop table Grupos

create table Jogos(
CodigoTimeA int,
CodigoTimeB int,
GolsTimeA int,
GolsTimeB int,
Data_Jogo date
primary key(CodigoTimeA,CodigoTimeB),
foreign key (CodigoTimeA) references Times(CodigoTime),
foreign key (CodigoTimeB) references Times(CodigoTime))

INSERT INTO Times VALUES
(1,	'Botafogo-SP',	'Ribeirão Preto',	'Santa cruz'),
(2,	'Bragantno',	'Bragança Pauísta',	'Nabi Abi Chedid'),
(3,	'Corinthians',	'São Paulo',	'Arena Corinthians'),
(4,	'Ferroviária',	'Araraquara',	'Fonte Luminosa'),
(5,	'Guarani',	'Campinas',	'Brinco de Ouro da Princesa'),
(6,	'Ituano',	'Itu',	'Novelli Júnior'),
(7,	'Mirassol',	'Mirassol',	'José Maria de Campos Maia'),
(8,	'Novorizontino',	'Novo Horizonte',	'Jorge Ismael de Biasi'),
(9,	'Oeste',	'Barueri',	'Arena Barueri'),
(10,	'Paimeiras',	'São Paulo',	'Alianz parque'),
(11,	'Ponte Preta',	'Campinas',	'Moisés Lucarelli'),
(12,	'Red Bul Brasil',	'Campinas',	'Moisés Lucarelli'),
(13,	'Santos',	'Santos',	'Vila Belmiro'),
(14,	'São Bento',	'Sorocaba',	'Walter Ribeiro'),
(15,	'São Caetano',	'São Caetano do Sul',	'Anacletto Campanella'),
(16,	'São Paulo',	'São Pauto',	'Morumbi')



select COUNT(NomeTime) from Times

select COUNT(*) from Grupos


exec sp_insereGrupoTimes
select * from Grupos

CREATE PROCEDURE sp_insereGrupoTimes
AS
	declare @numTimes int,
			@cont int,
			@grupo int
	set @cont = (select COUNT(*) from Grupos) + 1
	if (@cont = 1)
	BEGIN
		insert into Grupos values 
		('A',3),
		('B',13),
		('C',10),
		('D',16)
	END
	set @numTimes = (select COUNT(NomeTime) from Times)
	
	while @cont <= @numTimes
	BEGIN 
		set @grupo = CAST(RAND()*(5-1)+1 as int)
		print @cont
		if @cont != 3 and @cont != 13 and @cont != 10 and @cont != 16
		BEGIN
			IF @grupo = 1 
			BEGIN
				IF (SELECT COUNT(*) from Grupos where Grupo = 'A') < 4
				BEGIN
					insert into Grupos values ('A',@cont)
					set @cont = @cont + 1
					continue
				END
			END
			IF @grupo = 2 
			BEGIN
				IF (SELECT COUNT(*) from Grupos where Grupo = 'B') < 4
				BEGIN
					insert into Grupos values ('B',@cont)
					set @cont = @cont + 1
					continue
				END
			END
			IF @grupo = 3 
				IF (SELECT COUNT(*) from Grupos where Grupo = 'C') < 4
				BEGIN
					insert into Grupos values ('C',@cont)
					set @cont = @cont + 1
					continue
				END
			IF @grupo = 4 
			BEGIN
				IF (SELECT COUNT(*) from Grupos where Grupo = 'D') < 4
				BEGIN
					insert into Grupos values ('D',@cont)
					set @cont = @cont + 1
					continue
				END
			END
		END
		ELSE
		BEGIN
			set @cont = @cont + 1
		END 
	END

CREATE PROCEDURE sp_insereJogosData
AS
	create table #tabela 
	(indice int null , numTime int null)
	create table #tabela2
	(indice int null , numTime int null)
	declare @vetorGrupos as varchar(5),@cont as int, @cont2 as int,@contTimeB as int, @timeA as int,  @timeB as int,@diaJogo as date,@contTimeA int,@dataJogo date,
	@contData int,@qnt int
	set @vetorGrupos = 'ABCD'
	set @cont = 1
	Delete from Jogos
	while @cont <= 3
	BEGIN
		--define a data inicial
		if (SUBSTRING(@vetorGrupos, @cont, 1) = 'B')
		BEGIN
			set @dataJogo = '2019-02-17'
			
		END
		ELSE
		BEGIN
			set @dataJogo = '2019-01-20'
		END
		--carrega a tabela 1 (Time A)
		set @cont2 = @cont + 1
		set @contTimeA = 1
		Delete from #tabela
		insert into #tabela (indice,numTime) select ROW_NUMBER() OVER(ORDER BY g.CodigoTime) as indice,g.CodigoTime as numTime from Grupos g where g.Grupo = SUBSTRING(@vetorGrupos, @cont, 1)
		
		while @cont2 <= 4
		BEGIN
			--carrega a tabela 2 (time B)
			DELETE from #tabela2
			insert into #tabela2 (indice,numTime) select ROW_NUMBER() OVER(ORDER BY g.CodigoTime) as indice,g.CodigoTime as numTime from Grupos g where g.Grupo = SUBSTRING(@vetorGrupos, @cont2, 1)
			set @contTimeA = 1
			while @contTimeA <= 4
			BEGIN
				--carrega um time da tabela 1 na variável @timeA
				print SUBSTRING(@vetorGrupos, @cont, 1)+ 'x' + SUBSTRING(@vetorGrupos, @cont2, 1)
				set @timeA = (select numTime from #tabela where indice = @contTimeA)
				set @contTimeB = 1
				set @diaJogo = @dataJogo
				while @contTimeB <= 4
				BEGIN
					--carrega 1 time da tabela 2 na variavel @timeB e faz um jogo vs o @timeA
					set @timeB = (SELECT numTime FROM #tabela2 where indice = @contTimeB)
					set @qnt = (select COUNT(*) from Jogos j where j.Data_Jogo = @diaJogo and (j.CodigoTimeA = @timeA or j.CodigoTimeB = @timeB or j.CodigoTimeA = @timeB or j.CodigoTimeB = @timeA))
					if @qnt < 1
					BEGIN
						--caso n tenha nenhum jogo de ambos os times na data @diaJogo, a partida é inserida
						Insert into Jogos Values(@timeA,@timeB,0,0,@diaJogo)
						print 'Inserindo jogo ' + Cast(@timeA as varchar) + ' x '+ Cast(@timeB as varchar) + ' no dia ' + Cast(@diaJogo as varchar)
						set @contTimeB = @contTimeB + 1
						set @diaJogo = @dataJogo
					END
					ELSE
					BEGIN
						--se já existir um jogo a próxima data é carregada
						if DATEPART(weekday,@diaJogo) = 1
						BEGIN
							set @diaJogo = DATEADD(day, 3, @diaJogo);
						END
						ELSE
						BEGIN
							set @diaJogo = DATEADD(day, 4, @diaJogo);
						END
					END
				END
				set @contTimeA = @contTimeA + 1
			END
			--define a proxima data inicial
			set @contData = 1
			if (SUBSTRING(@vetorGrupos, @cont, 1) = 'B')
			BEGIN
				while @contData <= 4
				BEGIN
					if DATEPART(weekday,@dataJogo) = 1
				BEGIN
					set @dataJogo = DATEADD(day, -3, @dataJogo);
				END
				ELSE
				BEGIN
					set @dataJogo = DATEADD(day, -4, @dataJogo);
				END
					set @contData = @contData + 1
				END
			END
			ELSE
			BEGIN
				while @contData <= 3
				BEGIN
					if DATEPART(weekday,@dataJogo) = 1
					BEGIN
						set @dataJogo = DATEADD(day, 3, @dataJogo);
					END
					ELSE
					BEGIN
						set @dataJogo = DATEADD(day, 4, @dataJogo);
					END
					set @contData = @contData + 1	
				END
			END

			print @dataJogo
			set @cont2 = @cont2 + 1
		END
		set @cont = @cont + 1
	END
	

