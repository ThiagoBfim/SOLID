package cotuba;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.apache.commons.cli.*;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Options options = new Options();

		Option opcaoDeDiretorioDosMD = new Option("d", "dir", true,
				"Diretório que contem os arquivos md. Default: diretório atual.");
		options.addOption(opcaoDeDiretorioDosMD);

		Option opcaoDeFormatoDoEbook = new Option("f", "format", true,
				"Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
		options.addOption(opcaoDeFormatoDoEbook);

		Option opcaoDeArquivoDeSaida = new Option("o", "output", true,
				"Arquivo de saída do ebook. Default: book.{formato}.");
		options.addOption(opcaoDeArquivoDeSaida);

		Option opcaoModoVerboso = new Option("v", "verbose", false,
				"Habilita modo verboso.");
		options.addOption(opcaoModoVerboso);
		
		CommandLineParser cmdParser = new DefaultParser();
		HelpFormatter ajuda = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = cmdParser.parse(options, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			ajuda.printHelp("cotuba", options);
			System.exit(1);
			return;
		}

		Path diretorioDosMD;
		String formato;
		Path arquivoDeSaida;
		boolean modoVerboso = false;

		try {

			String nomeDoDiretorioDosMD = cmd.getOptionValue("dir");

			if (nomeDoDiretorioDosMD != null) {
				diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
				if (!Files.isDirectory(diretorioDosMD)) {
					throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
				}
			} else {
				Path diretorioAtual = Paths.get("");
				diretorioDosMD = diretorioAtual;
			}

			String nomeDoFormatoDoEbook = cmd.getOptionValue("format");

			if (nomeDoFormatoDoEbook != null) {
				formato = nomeDoFormatoDoEbook.toLowerCase();
			} else {
				formato = "pdf";
			}

			String nomeDoArquivoDeSaidaDoEbook = cmd.getOptionValue("output");
			if (nomeDoArquivoDeSaidaDoEbook != null) {
				arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
				if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
					throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
				}
			} else {
				arquivoDeSaida = Paths.get("book." + formato.toLowerCase());
			}

			modoVerboso = cmd.hasOption("verbose");
			
			if ("pdf".equals(formato)) {
				try(PdfWriter writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
					PdfDocument pdf = new PdfDocument(writer);
					Document pdfDocument = new Document(pdf)) {

					PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
					try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
						arquivosMD
							.filter(matcher::matches)
							.sorted()
							.forEach(arquivoMD -> {
								Parser parser = Parser.builder().build();
								Node document = null;
								try {
									document = parser.parseReader(Files.newBufferedReader(arquivoMD));
									document.accept(new AbstractVisitor() {
										@Override
										public void visit(Heading heading) {
											if (heading.getLevel() == 1) {
												// capítulo
												String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
												// TODO: usar título do capítulo
											} else if (heading.getLevel() == 2) {
												// seção
											} else if (heading.getLevel() == 3) {
												// título
											}
										}

									});
								} catch (Exception ex) {
									throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
								}

								try {
									HtmlRenderer renderer = HtmlRenderer.builder().build();
									String html = renderer.render(document);

									List<IElement> convertToElements = HtmlConverter.convertToElements(html);
									for (IElement element : convertToElements) {
										pdfDocument.add((IBlockElement) element);
									}
									// TODO: não adicionar página depois do último capítulo
									pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

								} catch (Exception ex) {
									throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
								}

							});
					} catch (IOException ex) {
						throw new RuntimeException(
								"Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
					}

				} catch (Exception ex) {
					throw new RuntimeException("Erro ao criar arquivo PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
				}

			} else if ("epub".equals(formato)) {
				Book epub = new Book();

				PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
				try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
					arquivosMD
						.filter(matcher::matches)
						.sorted()
						.forEach(arquivoMD -> {
							Parser parser = Parser.builder().build();
							Node document = null;
							try {
								document = parser.parseReader(Files.newBufferedReader(arquivoMD));
								document.accept(new AbstractVisitor() {
									@Override
									public void visit(Heading heading) {
										if (heading.getLevel() == 1) {
											// capítulo
											String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
											// TODO: usar título do capítulo
										} else if (heading.getLevel() == 2) {
											// seção
										} else if (heading.getLevel() == 3) {
											// título
										}
									}

								});
							} catch (Exception ex) {
								throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
							}

							try {
								HtmlRenderer renderer = HtmlRenderer.builder().build();
								String html = renderer.render(document);

								// TODO: usar título do capítulo
								epub.addSection("Capítulo", new Resource(html.getBytes(), MediatypeService.XHTML));

							} catch (Exception ex) {
								throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
							}
						});
				} catch (IOException ex) {
					throw new RuntimeException(
							"Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
				}

				EpubWriter epubWriter = new EpubWriter();

				try {
					epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
				} catch (IOException ex) {
					throw new RuntimeException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
				}
			} else {
				throw new RuntimeException("Formato do ebook inválido: " + formato);
			}

			System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			if (modoVerboso) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}
