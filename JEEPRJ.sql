--
-- Database: `JEEPRJ`
--

-- --------------------------------------------------------

--
-- Table structure for table `EMPLOYES`
--

CREATE TABLE `EMPLOYES` (
  `ID` int(11) NOT NULL,
  `MOTDEPASSE` varchar(10) NOT NULL,
  `NOM` varchar(25) DEFAULT NULL,
  `PRENOM` varchar(25) DEFAULT NULL,
  `TELDOM` varchar(10) DEFAULT NULL,
  `TELPORT` varchar(10) DEFAULT NULL,
  `TELPRO` varchar(10) DEFAULT NULL,
  `ADRESSE` varchar(150) DEFAULT NULL,
  `CODEPOSTAL` varchar(15) DEFAULT NULL,
  `VILLE` varchar(25) DEFAULT NULL,
  `EMAIL` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `EMPLOYES`
--

INSERT INTO `EMPLOYES` (`ID`, `MOTDEPASSE`, `NOM`, `PRENOM`, `TELDOM`, `TELPORT`, `TELPRO`, `ADRESSE`, `CODEPOSTAL`, `VILLE`, `EMAIL`) VALUES
(1, 'Bond', 'Bond', 'James', '0123456789', '0612345678', '0698765432', '2 avenue 007', '92700', 'Colombes', 'jbond@gmail.com'),
(2, 'Jones', 'Jones', 'Indiana', '0145362787', '0645362718', '0611563477', '10 rue des Aventuriers', '92270', 'Bois-colombes', 'ijones@gmail.com'),
(3, 'Bourne', 'Bourne', 'Jason', '0187665987', '0623334256', '0654778654', '65 rue Agent Secret Perdu', '92700', 'Colombes', 'jbourne@yahoo.fr'),
(4, 'Stark', 'Stark', 'Arya', '0187611987', '0783334256', '0658878654', '6 rue Sans-Nom', '75016', 'Paris', 'astark@nord.com'),
(5, 'Lanister', 'Lanister', 'Cersei', '0187384987', '0622494256', '0674178654', '5 bvd des Reines', '21000', 'Dijon', 'clanister@mail.co.uk');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `EMPLOYES`
--
ALTER TABLE `EMPLOYES`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `EMPLOYES`
--
ALTER TABLE `EMPLOYES`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
