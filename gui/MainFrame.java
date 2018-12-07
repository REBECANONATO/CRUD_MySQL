package br.edu.devmedia.jdbc.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.devmedia.jdbc.bo.PessoaBO;
import br.edu.devmedia.jdbc.bo.UfBO;
import br.edu.devmedia.jdbc.dao.PessoaDAO;
import br.edu.devmedia.jdbc.dto.EnderecoDTO;
import br.edu.devmedia.jdbc.dto.PessoaDTO;
import br.edu.devmedia.jdbc.dto.UfDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;
import br.edu.devmedia.jdbc.util.MensagensUtil;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("unused")
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7769221862541181210L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtDtNasc;
	private JRadioButton rbtFeminino;
	private JRadioButton rbtMasculino;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private JTable tableListagem;
	private JTextField txtNomeConsultar;
	private JTextField txtCpfConsultar;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTable tableListConsultar;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private List<Integer> idsPessoas = new ArrayList<>();
	private JTextField txtNomeUpdate;
	private JTextField txtCpfUpdate;
	private JTextField txtDtNascUpdate;
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private JTextField txtLogradouro;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtCep;
	@SuppressWarnings("rawtypes")
	private JComboBox comboUf;
	@SuppressWarnings("rawtypes")
	private JComboBox comboUfUpdate;
	private JTextField txtLograUpdate;
	private JTextField txtBairroUpdate;
	private JTextField txtCidadeUpdate;
	private JTextField txtNumeroUpdate;
	private JTextField txtCepUpdate;
	private PessoaDTO pessoaDTO; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {

		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		UfBO ufBO = new UfBO();

		// **************************************************************************************************************************************************************************

		JPanel panelCadastro = new JPanel();
		JPanel panelCadastroPrincipal = new JPanel();
		tabbedPane.addTab("Cadastro", null, panelCadastro, null);

		JButton btnCadastrar = new JButton("Cadastrar");
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtCpf.setText("");
				txtDtNasc.setText("");			
				txtLogradouro.setText("");
				txtBairro.setText("");
				txtCidade.setText("");
				txtCep.setText("");
				txtNumero.setText("");
				comboUf.setSelectedIndex(0);
				rbtMasculino.setSelected(true);
			}
		});

		
		panelCadastroPrincipal.setBorder(
				new TitledBorder(null, "CadastroPrincipal", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelEnderecoCadastro = new JPanel();
		panelEnderecoCadastro
				.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GroupLayout gl_panelCadastro = new GroupLayout(panelCadastro);
		gl_panelCadastro
				.setHorizontalGroup(
						gl_panelCadastro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCadastro.createSequentialGroup().addContainerGap()
										.addComponent(panelCadastroPrincipal, GroupLayout.PREFERRED_SIZE, 308,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(panelEnderecoCadastro, GroupLayout.PREFERRED_SIZE, 375,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(35, Short.MAX_VALUE))
								.addGroup(gl_panelCadastro.createSequentialGroup().addContainerGap(548, Short.MAX_VALUE)
										.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnCadastrar).addContainerGap()));
		gl_panelCadastro.setVerticalGroup(gl_panelCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastro.createSequentialGroup()
						.addGroup(gl_panelCadastro.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_panelCadastro.createSequentialGroup().addGap(266)
												.addGroup(gl_panelCadastro.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnCadastrar).addComponent(btnLimpar)))
								.addGroup(gl_panelCadastro.createSequentialGroup().addContainerGap()
										.addGroup(gl_panelCadastro.createParallelGroup(Alignment.BASELINE)
												.addComponent(panelCadastroPrincipal, GroupLayout.PREFERRED_SIZE, 203,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(panelEnderecoCadastro, GroupLayout.PREFERRED_SIZE, 203,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JLabel lblLogradouro = new JLabel("Logradouro:");

		txtLogradouro = new JTextField();
		txtLogradouro.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");

		txtBairro = new JTextField();
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");

		txtCidade = new JTextField();
		txtCidade.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero:");

		txtNumero = new JTextField();
		txtNumero.setColumns(10);

		JLabel lblCep = new JLabel("CEP:");

		txtCep = new JTextField();
		txtCep.setColumns(10);

		JLabel lblUf = new JLabel("UF:");

		GroupLayout gl_panelEnderecoCadastro = new GroupLayout(panelEnderecoCadastro);
		gl_panelEnderecoCadastro.setHorizontalGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEnderecoCadastro.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelEnderecoCadastro.createSequentialGroup()
										.addComponent(lblLogradouro, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtLogradouro,
												GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panelEnderecoCadastro.createSequentialGroup()
										.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, 203,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.TRAILING,
										gl_panelEnderecoCadastro.createSequentialGroup()
												.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 78,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtBairro,
														GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelEnderecoCadastro.createSequentialGroup()
										.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtNumero,
												GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelEnderecoCadastro.createSequentialGroup()
										.addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtCep,
												GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelEnderecoCadastro.createSequentialGroup()
										.addComponent(lblUf, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getComboUf(), 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))) //rebeca
						.addContainerGap(68, Short.MAX_VALUE)));
		gl_panelEnderecoCadastro.setVerticalGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEnderecoCadastro.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLogradouro).addComponent(txtLogradouro, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBairro))
						.addGap(8)
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCidade))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNumero))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.BASELINE).addComponent(lblCep)
								.addComponent(txtCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelEnderecoCadastro.createParallelGroup(Alignment.BASELINE).addComponent(lblUf)
								.addComponent(getComboUf(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(13, Short.MAX_VALUE)));
		panelEnderecoCadastro.setLayout(gl_panelEnderecoCadastro);

		txtNome = new JTextField();
		txtNome.setColumns(10);

		JLabel lblNome = new JLabel("Nome: ");

		JLabel lblCpf = new JLabel("Cpf:");

		txtCpf = new JTextField();
		txtCpf.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo:");

		JRadioButton rbtMasculino = new JRadioButton("Masculino");
		rbtMasculino.setSelected(true);
		buttonGroup.add(rbtMasculino);

		JRadioButton rbtFeminino = new JRadioButton("Feminino");
		rbtFeminino.setSelected(true);
		buttonGroup.add(rbtFeminino);

		JLabel lblDtNasc = new JLabel("Dt. Nasc:");

		txtDtNasc = new JTextField();
		txtDtNasc.setColumns(10);
		GroupLayout gl_panelCadastroPrincipal = new GroupLayout(panelCadastroPrincipal);
		gl_panelCadastroPrincipal.setHorizontalGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastroPrincipal.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panelCadastroPrincipal.createSequentialGroup()
										.addComponent(lblDtNasc, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtDtNasc, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(gl_panelCadastroPrincipal.createSequentialGroup()
										.addComponent(lblSexo, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(rbtMasculino)
										.addGap(29)
										.addComponent(rbtFeminino, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2))
								.addGroup(Alignment.TRAILING, gl_panelCadastroPrincipal.createSequentialGroup()
										.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelCadastroPrincipal.createSequentialGroup()
														.addComponent(lblCpf, GroupLayout.DEFAULT_SIZE, 61,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(gl_panelCadastroPrincipal.createSequentialGroup()
														.addComponent(lblNome).addGap(31)))
										.addGroup(gl_panelCadastroPrincipal
												.createParallelGroup(Alignment.LEADING, false).addComponent(txtNome)
												.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
										.addContainerGap()))));
		gl_panelCadastroPrincipal.setVerticalGroup(
				gl_panelCadastroPrincipal.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_panelCadastroPrincipal.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE)
								.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome))
								.addGap(18)
								.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCpf))
								.addGap(18)
								.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.BASELINE)
												.addComponent(rbtMasculino).addComponent(lblSexo))
										.addComponent(rbtFeminino))
								.addGap(18)
								.addGroup(gl_panelCadastroPrincipal.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtDtNasc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDtNasc))
								.addContainerGap()));
		panelCadastroPrincipal.setLayout(gl_panelCadastroPrincipal);
		panelCadastro.setLayout(gl_panelCadastro);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PessoaDTO pessoaDTO = new PessoaDTO();
				EnderecoDTO enderecoDTO = new EnderecoDTO();
				PessoaBO pessoaBO = new PessoaBO();
				try {

					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String nasc = txtDtNasc.getText();

					pessoaBO.validaNome(nome);
					pessoaBO.validaCpf(cpf);
					pessoaBO.validaDtNasc(nasc);

					pessoaDTO.setNome(nome);
					pessoaDTO.setCpf(Long.parseLong(cpf));

					try {
						pessoaDTO.setDtNascimento(dateFormat.parse(nasc));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					char sexo = rbtMasculino.isSelected() ? 'M' : 'F';
					pessoaDTO.setSexo(sexo);
										
					enderecoDTO.setLogradouro(txtLogradouro.getText());
					enderecoDTO.setBairro(txtBairro.getText());
					enderecoDTO.setCep(txtCep.getText().equals("") ? null : Integer.parseInt(txtCep.getText()));
					enderecoDTO.setCidade(txtCidade.getText());
					enderecoDTO.setNumero(txtNumero.getText().equals("") ? null : Long.parseLong(txtNumero.getText()));
					
					pessoaBO.validaEndereco(enderecoDTO);
					
					UfDTO ufDTO = new UfDTO();
					ufDTO.setIdUf(getComboUf().getSelectedIndex() + 1);
					enderecoDTO.setUfDTO(ufDTO);
					
					pessoaDTO.setEnderecoDTO(enderecoDTO);
					pessoaBO.cadastrar(pessoaDTO);

					MensagensUtil.addMsg(MainFrame.this, "Cadastro Efetuado com sucesso");
					MainFrame.this.dispose();
					main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
					MensagensUtil.addMsg(MainFrame.this, e1.getMessage());
				}

			}
		});


//**************************************************************************************************************************************************************************
		JPanel panelListagem = new JPanel();
		tabbedPane.addTab("Listagem", null, panelListagem, null);
		tabbedPane.setEnabledAt(1, true);

		JScrollPane scrollListagem = new JScrollPane();

		JButton btnDeletar = new JButton("Deletar Tudo");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// deletar
				PessoaBO pessoaBO = new PessoaBO();
				try {
					int resp = JOptionPane.showConfirmDialog(MainFrame.this, "Deseja Realmente Deletar TUDO?");
					if (resp == 0) {
						pessoaBO.removeTudo();
						MensagensUtil.addMsg(MainFrame.this, "Pessoas removidas com sucesso!!!");
						MainFrame.this.dispose();
						main(null);
					}

				} catch (NegocioException e) {
					e.printStackTrace();
					MensagensUtil.addMsg(MainFrame.this, e.getMessage());
				}

			}
		});

		JInternalFrame internalFrame = new JInternalFrame("Editar Listagem");
		GroupLayout gl_panelListagem = new GroupLayout(panelListagem);
		gl_panelListagem.setHorizontalGroup(
			gl_panelListagem.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelListagem.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelListagem.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollListagem, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
						.addComponent(btnDeletar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addComponent(internalFrame, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
		);
		gl_panelListagem.setVerticalGroup(
			gl_panelListagem.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelListagem.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDeletar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollListagem, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(internalFrame, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
		);

		txtNomeUpdate = new JTextField();
		txtNomeUpdate.setColumns(10);

		JLabel lblIdUpdate = new JLabel("ID:");

		JLabel lblNomeUpdate = new JLabel("Nome:");

		JLabel lblCpfUpdate = new JLabel("CPF:");

		JLabel lblSexoUpdate = new JLabel("Sexo:");

		JLabel lblDtNascUpdate = new JLabel("Dt. Nasc:");

		txtCpfUpdate = new JTextField();
		txtCpfUpdate.setColumns(10);

		txtDtNascUpdate = new JTextField();
		txtDtNascUpdate.setColumns(10);

		JRadioButton rdbtnFeminUpdate = new JRadioButton("Femin");
		buttonGroup_3.add(rdbtnFeminUpdate);

		JRadioButton rdbtnMascUpdate = new JRadioButton("Masc");
		buttonGroup_3.add(rdbtnMascUpdate);
		JLabel lblIdValor = new JLabel("");

		JButton BtnUpdate = new JButton("Update");
		BtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaBO pessoaBO = new PessoaBO();
				PessoaDTO pessoaDTO = new PessoaDTO();
				EnderecoDTO enderecoDTO = new EnderecoDTO();
				
				try {
										
					String nome = txtNomeUpdate.getText();
					String cpf = txtCpfUpdate.getText();
					String nasc = txtDtNascUpdate.getText();

					pessoaBO.validaNome(nome);
					pessoaBO.validaCpf(cpf);
					pessoaBO.validaDtNasc(nasc);

					pessoaDTO.setIdPessoa(Integer.parseInt(lblIdValor.getText()));
					pessoaDTO.setNome(nome);
					pessoaDTO.setCpf(Long.parseLong(cpf));
					
					pessoaDTO.setDtNascimento(dateFormat.parse(nasc));
					char sexo = rdbtnMascUpdate.isSelected() ? 'M' : 'F';
					pessoaDTO.setSexo(sexo);
					
					
					enderecoDTO.setIdEndereco(getPessoaDTO().getEnderecoDTO().getIdEndereco());
					enderecoDTO.setLogradouro(txtLograUpdate.getText());
					enderecoDTO.setBairro(txtBairroUpdate.getText());
					enderecoDTO.setCep(txtCepUpdate.getText().equals("") ? null : Integer.parseInt(txtCepUpdate.getText()));
					enderecoDTO.setCidade(txtCidadeUpdate.getText());
					enderecoDTO.setNumero(txtNumeroUpdate.getText().equals("") ? null : Long.parseLong(txtNumeroUpdate.getText()));
					
					pessoaBO.validaEndereco(enderecoDTO);
					
					UfDTO ufDTO = new UfDTO();
					ufDTO.setIdUf(comboUfUpdate.getSelectedIndex() + 1);
					enderecoDTO.setUfDTO(ufDTO);
					
					pessoaDTO.setEnderecoDTO(enderecoDTO);
					pessoaDTO.setIdPessoa(getPessoaDTO().getIdPessoa());
					PessoaDAO pessoaDAO = new PessoaDAO();
					pessoaDAO.atualizar(pessoaDTO);
					
					MensagensUtil.addMsg(MainFrame.this, "Pessoa atualizada com sucesso!");
					MainFrame.this.dispose();
					main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
					MensagensUtil.addMsg(MainFrame.this, e1.getMessage());
				}

			}
		});
		
		JPanel panel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(internalFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIdUpdate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblIdValor))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNomeUpdate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtNomeUpdate, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCpfUpdate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtCpfUpdate, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDtNascUpdate, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtDtNascUpdate, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSexoUpdate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(rdbtnFeminUpdate)
							.addGap(18)
							.addComponent(rdbtnMascUpdate, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addGap(243))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 673, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(590, Short.MAX_VALUE)
					.addComponent(BtnUpdate)
					.addGap(71))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIdUpdate)
								.addComponent(lblIdValor))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomeUpdate)
								.addComponent(txtNomeUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCpfUpdate)
								.addComponent(txtCpfUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSexoUpdate)
								.addComponent(rdbtnFeminUpdate)
								.addComponent(rdbtnMascUpdate))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDtNascUpdate)
								.addComponent(txtDtNascUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(BtnUpdate)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		JLabel lblLograUpdate = new JLabel("Logradouro:");
		
		txtLograUpdate = new JTextField();
		txtLograUpdate.setColumns(10);
		
		JLabel lblBairroUpdate = new JLabel("Bairro:");
		
		txtBairroUpdate = new JTextField();
		txtBairroUpdate.setColumns(10);
		
		JLabel lblCidadeUpdate = new JLabel("Cidade:");
		
		txtCidadeUpdate = new JTextField();
		txtCidadeUpdate.setColumns(10);
		
		txtNumeroUpdate = new JTextField();
		txtNumeroUpdate.setColumns(10);
		
		JLabel lblNumeroUpdate = new JLabel("N\u00FAmero:");
		
		JLabel lblCepUpdate = new JLabel("CEP:");
		
		txtCepUpdate = new JTextField();
		txtCepUpdate.setColumns(10);
		
		JLabel lblUfUpdate = new JLabel("UF:");
		

		

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCidadeUpdate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCidadeUpdate, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNumeroUpdate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtNumeroUpdate, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCepUpdate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtCepUpdate, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblUfUpdate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(getJComboBoxUpdateUf(), GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
							.addGap(29))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblBairroUpdate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtBairroUpdate, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblLograUpdate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtLograUpdate, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(378, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNumeroUpdate))
								.addComponent(txtNumeroUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblCepUpdate))
								.addComponent(txtCepUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUfUpdate)
								.addComponent(comboUfUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblLograUpdate))
								.addComponent(txtLograUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblBairroUpdate))
								.addComponent(txtBairroUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCidadeUpdate)
								.addComponent(txtCidadeUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		internalFrame.getContentPane().setLayout(groupLayout);

		PessoaBO pessoaBO = new PessoaBO();
		String[][] lista;
		try {
			lista = pessoaBO.listagem(idsPessoas);

			tableListagem = new JTable();
			tableListagem.setModel(new DefaultTableModel(lista,
					new String[] { "Id", "Nome", "CPF", "Sexo", "Dt. Nasc.", "Logradouro", "CEP", "UF", "", "" }) {
				private static final long serialVersionUID = -545467246473218452L;
				boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, true, true };

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			scrollListagem.setViewportView(tableListagem);
			panelListagem.setLayout(gl_panelListagem);

			Action actionDelecao = new AbstractAction() {
				private static final long serialVersionUID = -421951775656187572L;

				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					int resp = JOptionPane.showConfirmDialog(MainFrame.this, "Deseja realmente deletar essa pessoa?");
					if (resp == 0) {
						JTable table = (JTable) actionEvent.getSource();
						int linha = Integer.parseInt(actionEvent.getActionCommand());
						((DefaultTableModel) table.getModel()).removeRow(linha);
						try {
							pessoaDTO = pessoaBO.buscaPorId(idsPessoas.get(linha));
							pessoaBO.removePessoa(idsPessoas.get(linha), getPessoaDTO().getEnderecoDTO().getIdEndereco());
							idsPessoas.remove(linha);
							MensagensUtil.addMsg(MainFrame.this, "Removeu com Sucesso");
						} catch (NegocioException e) {
							e.printStackTrace();
							MensagensUtil.addMsg(MainFrame.this, e.getMessage());
						}

					}
				}
				
			};

			Action actionEdicao = new AbstractAction() {
							
				public void actionPerformed(ActionEvent actionEvent) {
					int linha = Integer.parseInt(actionEvent.getActionCommand());
					try {
						pessoaDTO = pessoaBO.buscaPorId(idsPessoas.get(linha));
						lblIdValor.setText(pessoaDTO.getIdPessoa().toString());
						txtNomeUpdate.setText(pessoaDTO.getNome());
						txtCpfUpdate.setText(pessoaDTO.getCpf().toString());
						
						txtDtNascUpdate.setText(dateFormat.format(pessoaDTO.getDtNascimento()));
						rdbtnMascUpdate.setSelected(pessoaDTO.getSexo() == 'M');
						rdbtnFeminUpdate.setSelected(pessoaDTO.getSexo() == 'F');
						
						EnderecoDTO endereco = pessoaDTO.getEnderecoDTO();
						txtLograUpdate.setText(endereco.getLogradouro());
						txtBairroUpdate.setText(endereco.getBairro());
						txtCidadeUpdate.setText(endereco.getCidade());
					    txtNumeroUpdate.setText(endereco.getNumero().toString());
						txtCepUpdate.setText(endereco.getCep().toString());
						comboUfUpdate.setSelectedIndex(endereco.getUfDTO().getIdUf() - 1);
						internalFrame.setVisible(true);

					} catch (NegocioException e) {
						e.printStackTrace();
						MensagensUtil.addMsg(MainFrame.this, e.getMessage());
					}
				}
			};

			ButtonColumn buttonColumnDelecao = new ButtonColumn(tableListagem, actionDelecao, 9);
			ButtonColumn buttonColumnEdicao = new ButtonColumn(tableListagem, actionEdicao, 8);
			buttonColumnDelecao.setMnemonic(KeyEvent.VK_D);
			buttonColumnEdicao.setMnemonic(KeyEvent.VK_E);

		} catch (NegocioException e1) {
			e1.printStackTrace();
			MensagensUtil.addMsg(MainFrame.this, e1.getMessage());
		}

//**************************************************************************************************************************************************************************
		JPanel Consultar = new JPanel();
		tabbedPane.addTab("Consultar", null, Consultar, null);

		JLabel lblNomeConsultar = new JLabel("Nome:");

		txtNomeConsultar = new JTextField();
		txtNomeConsultar.setText("");
		txtNomeConsultar.setColumns(10);

		JLabel lblCpfConsultar = new JLabel("CPF:");

		txtCpfConsultar = new JTextField();
		txtCpfConsultar.setText("");
		txtCpfConsultar.setColumns(10);

		JLabel lblSexoConsultar = new JLabel("Sexo:");

		JRadioButton rdbtnMasculinoConsultar = new JRadioButton("Masculino");
		rdbtnMasculinoConsultar.setSelected(true);
		buttonGroup_1.add(rdbtnMasculinoConsultar);

		JRadioButton rdbtnFemininoConsultar = new JRadioButton("Feminino");
		buttonGroup_1.add(rdbtnFemininoConsultar);

		JButton btnConsultar = new JButton("Consultar");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");

		tableListConsultar = new JTable();

		scrollPane.setViewportView(tableListConsultar);

		JLabel lblOrderby = new JLabel("OrderBy:");

		JRadioButton rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setSelected(true);
		buttonGroup_2.add(rdbtnNome);

		JRadioButton rdbtnCpf = new JRadioButton("CPF");
		rdbtnCpf.setSelected(true);
		buttonGroup_2.add(rdbtnCpf);
		GroupLayout gl_Consultar = new GroupLayout(Consultar);
		gl_Consultar.setHorizontalGroup(gl_Consultar.createParallelGroup(Alignment.LEADING).addGroup(gl_Consultar
				.createSequentialGroup().addGap(10)
				.addGroup(gl_Consultar.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
						.addGroup(gl_Consultar.createSequentialGroup().addGroup(gl_Consultar
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_Consultar.createSequentialGroup().addComponent(lblNomeConsultar).addGap(5)
										.addComponent(txtNomeConsultar, GroupLayout.PREFERRED_SIZE, 185,
												GroupLayout.PREFERRED_SIZE)
										.addGap(28).addComponent(lblCpfConsultar).addGap(18)
										.addComponent(txtCpfConsultar, GroupLayout.PREFERRED_SIZE, 163,
												GroupLayout.PREFERRED_SIZE)
										.addGap(66))
								.addGroup(gl_Consultar.createSequentialGroup().addComponent(lblSexoConsultar).addGap(18)
										.addComponent(rdbtnMasculinoConsultar).addGap(2)
										.addComponent(rdbtnFemininoConsultar, GroupLayout.PREFERRED_SIZE, 93,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblOrderby).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(rdbtnNome).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(rdbtnCpf).addGap(120)))
								.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
								.addComponent(btnConsultar)))
				.addContainerGap()));
		gl_Consultar.setVerticalGroup(gl_Consultar.createParallelGroup(Alignment.LEADING).addGroup(gl_Consultar
				.createSequentialGroup().addGap(11)
				.addGroup(gl_Consultar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Consultar.createSequentialGroup().addGap(3).addComponent(lblNomeConsultar))
						.addGroup(gl_Consultar.createSequentialGroup().addGap(3).addComponent(lblCpfConsultar))
						.addComponent(txtCpfConsultar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNomeConsultar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_Consultar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Consultar.createSequentialGroup().addGap(4).addComponent(lblSexoConsultar))
						.addComponent(rdbtnMasculinoConsultar)
						.addGroup(gl_Consultar.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnCpf)
								.addComponent(rdbtnNome).addComponent(lblOrderby).addComponent(btnConsultar))
						.addComponent(rdbtnFemininoConsultar))
				.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		Consultar.setLayout(gl_Consultar);

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nome = txtNomeConsultar.getText();
				Long cpf = txtCpfConsultar.getText().equals("") ? null : Long.parseLong(txtCpfConsultar.getText());
				char sexo = rdbtnMasculinoConsultar.isSelected() ? 'M' : 'F';
				String orderBy = rdbtnNome.isSelected() ? "nome" : "cpf";

				PessoaBO pessoaBO = new PessoaBO();
				try {
					String[][] lista = pessoaBO.listaConsulta(nome, cpf, sexo, orderBy);
					tableListConsultar.setModel(new DefaultTableModel(lista,
							new String[] { "Id", "Nome", "CPF", "Endereço", "Sexo", "Dt. Nasc." }) {
						private static final long serialVersionUID = -6292338430636247063L;
						boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
					MensagensUtil.addMsg(MainFrame.this, e.getMessage());
				}

			}
		});

// **************************************************************************************************************************************************************************

	}// Fim do Public MainFrame

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getJComboBoxUpdateUf() {
		if(comboUfUpdate == null) {
			try {
				UfBO ufBO = new UfBO();
				ComboBoxModel comboUfModel;
					comboUfModel = new DefaultComboBoxModel(converteEstados(ufBO.listaUfs()));
				comboUfUpdate = new JComboBox();
				comboUfUpdate.setModel(comboUfModel);
			} catch (NegocioException e) {
				e.printStackTrace();
				MensagensUtil.addMsg(MainFrame.this, e.getMessage());
			}
		}
		return comboUfUpdate;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getComboUf() {
		if(comboUf == null) {
			try {
				UfBO ufBO = new UfBO();
				ComboBoxModel comboUfModel;
				comboUfModel = new DefaultComboBoxModel(converteEstados(ufBO.listaUfs()));
				comboUf = new JComboBox();
				comboUf.setModel(comboUfModel);
			} catch (NegocioException e) {
				e.printStackTrace();
			}
		}
		return comboUf;
	}
	
	private PessoaDTO getPessoaDTO() {
		return pessoaDTO;
	}
	
	private String[] converteEstados(List<UfDTO> lista) {
		String[] result = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			UfDTO ufDTO = lista.get(i);
			result[i] = ufDTO.getDescricao();
		}
		return result;
	}

} // Fim da Classe
