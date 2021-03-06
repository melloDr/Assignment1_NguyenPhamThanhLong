USE [master]
GO
/****** Object:  Database [Assignment1_Nguyễn Phạm Thành Long]    Script Date: 4/11/2021 4:51:26 PM ******/
CREATE DATABASE [Assignment1_Nguyễn Phạm Thành Long]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Assignment1_Nguyễn Phạm Thành Long', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Assignment1_Nguyễn Phạm Thành Long.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Assignment1_Nguyễn Phạm Thành Long_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Assignment1_Nguyễn Phạm Thành Long_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Assignment1_Nguyễn Phạm Thành Long].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET RECOVERY FULL 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Assignment1_Nguyễn Phạm Thành Long', N'ON'
GO
USE [Assignment1_Nguyễn Phạm Thành Long]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 4/11/2021 4:51:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [nchar](20) NOT NULL,
	[categoryName] [nchar](20) NOT NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblFood]    Script Date: 4/11/2021 4:51:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblFood](
	[foodID] [nchar](20) NOT NULL,
	[name] [nchar](20) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[categoryID] [nchar](20) NOT NULL,
	[images] [nchar](50) NOT NULL,
	[dateAddNew] [date] NOT NULL,
	[status] [bit] NOT NULL,
	[dateOfUpdate] [date] NULL,
 CONSTRAINT [PK_tblFood] PRIMARY KEY CLUSTERED 
(
	[foodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 4/11/2021 4:51:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[foodID] [nchar](20) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[orderID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 4/11/2021 4:51:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [nchar](10) NOT NULL,
	[total] [float] NOT NULL,
	[dateBuy] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 4/11/2021 4:51:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUser](
	[userID] [nchar](10) NOT NULL,
	[password] [nchar](10) NOT NULL,
	[role] [nchar](10) NOT NULL,
	[name] [char](50) NOT NULL,
	[email] [nchar](50) NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'b                   ', N'bread               ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'c                   ', N'coffee              ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'ck                  ', N'cake                ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'd                   ', N'drink               ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'm                   ', N'milk                ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N's                   ', N'singum              ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N't                   ', N'tea                 ')
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f1                  ', N'cocacola            ', 9, 2.5, N'd                   ', N'coca.jpg                                          ', CAST(N'2020-02-09' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f10                 ', N'lipton              ', 12, 2, N't                   ', N'a10                                               ', CAST(N'2020-02-07' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f11                 ', N'red dragon          ', 5, 3, N'd                   ', N'a11                                               ', CAST(N'2020-08-09' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f12                 ', N'vinamilkkkkkk       ', 20, 2, N'm                   ', N'a12                                               ', CAST(N'2019-04-04' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f13                 ', N'doublemine          ', 27, 0.1, N's                   ', N'a13                                               ', CAST(N'2020-03-04' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f14                 ', N'pepsi               ', 40, 3, N'd                   ', N'a10                                               ', CAST(N'2020-06-02' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f15                 ', N'Deke                ', 19, 2, N'c                   ', N'a11                                               ', CAST(N'2020-08-18' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f16                 ', N'THtruemik           ', 20, 1.9, N'm                   ', N'a10                                               ', CAST(N'2020-01-15' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f17                 ', N'asd                 ', 1, 1.6, N'c                   ', N'dasd                                              ', CAST(N'2021-03-16' AS Date), 1, NULL)
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f18                 ', N'asd                 ', 1, 1.6, N'b                   ', N'                                                  ', CAST(N'2021-03-16' AS Date), 1, NULL)
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f2                  ', N'kinh do             ', 5, 1.2, N'b                   ', N'a2                                                ', CAST(N'2020-06-05' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f3                  ', N'pepsi               ', 4, 2.4, N'd                   ', N'a3                                                ', CAST(N'2020-10-22' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f4                  ', N'banh macaron        ', 7, 4.6, N'ck                  ', N'a4                                                ', CAST(N'2020-12-12' AS Date), 0, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f5                  ', N'banh tao            ', 9, 1.2, N'ck                  ', N'a5                                                ', CAST(N'2020-03-03' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f6                  ', N'nutriboost          ', 30, 0.6, N'd                   ', N'a6                                                ', CAST(N'2020-03-10' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f7                  ', N'banh tieu           ', 3, 0.2, N'ck                  ', N'a7                                                ', CAST(N'2020-01-24' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f8                  ', N'number one          ', 7, 1, N'd                   ', N'a8                                                ', CAST(N'2020-06-06' AS Date), 1, CAST(N'2021-01-19' AS Date))
INSERT [dbo].[tblFood] ([foodID], [name], [quantity], [price], [categoryID], [images], [dateAddNew], [status], [dateOfUpdate]) VALUES (N'f9                  ', N'latte               ', 8, 1.1, N'c                   ', N'a9                                                ', CAST(N'2020-08-08' AS Date), 1, CAST(N'2021-01-19' AS Date))
SET IDENTITY_INSERT [dbo].[tblOrderDetails] ON 

INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (15, N'f7                  ', 1, 0.2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (16, N'f10                 ', 1, 2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (17, N'f12                 ', 1, 2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (18, N'f1                  ', 1, 2.5, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (19, N'f7                  ', 1, 0.2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (20, N'f10                 ', 1, 2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (21, N'f7                  ', 1, 0.2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (22, N'f10                 ', 1, 2, 5)
INSERT [dbo].[tblOrderDetails] ([detailID], [foodID], [quantity], [price], [orderID]) VALUES (23, N'f12                 ', 1, 2, 5)
SET IDENTITY_INSERT [dbo].[tblOrderDetails] OFF
SET IDENTITY_INSERT [dbo].[tblOrders] ON 

INSERT [dbo].[tblOrders] ([orderID], [userID], [total], [dateBuy]) VALUES (5, N'long      ', 6.7, CAST(N'2021-01-18' AS Date))
SET IDENTITY_INSERT [dbo].[tblOrders] OFF
INSERT [dbo].[tblUser] ([userID], [password], [role], [name], [email]) VALUES (N'admin     ', N'123456    ', N'admin     ', N'Tk Test                                           ', NULL)
INSERT [dbo].[tblUser] ([userID], [password], [role], [name], [email]) VALUES (N'long      ', N'123456    ', N'member    ', N'Thanh Long                                        ', NULL)
INSERT [dbo].[tblUser] ([userID], [password], [role], [name], [email]) VALUES (N'test      ', N'123456    ', N'member    ', N'Tk Test                                           ', NULL)
INSERT [dbo].[tblUser] ([userID], [password], [role], [name], [email]) VALUES (N'tien      ', N'654321    ', N'admin     ', N'Ngoc Tien                                         ', NULL)
ALTER TABLE [dbo].[tblFood]  WITH CHECK ADD  CONSTRAINT [FK_tblFood_tblCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblFood] CHECK CONSTRAINT [FK_tblFood_tblCategory]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblFood] FOREIGN KEY([foodID])
REFERENCES [dbo].[tblFood] ([foodID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblFood]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblOrders] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblOrders]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK_tblOrders_tblUser] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK_tblOrders_tblUser]
GO
USE [master]
GO
ALTER DATABASE [Assignment1_Nguyễn Phạm Thành Long] SET  READ_WRITE 
GO
