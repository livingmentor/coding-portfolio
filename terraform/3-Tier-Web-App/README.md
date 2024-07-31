
# Three-Tier Web Application Infrastructure with Terraform

## Project Overview

This project provisions a three-tier web application infrastructure on AWS using Terraform. The infrastructure includes:
- A Virtual Private Cloud (VPC) with public and private subnets.
- An Internet Gateway and a NAT Gateway for internet access.
- Security Groups for managing access to the web, application, and database layers.
- An Application Load Balancer (ALB) with HTTPS offloading.
- EC2 instances for the web and application servers.
- An RDS instance for the database.


## Terraform Files

### `main.tf`

This file contains the main Terraform configuration, including:
- Provider configuration for AWS.
- VPC and subnet definitions.
- Internet Gateway and NAT Gateway.
- Route tables and associations.
- Security groups for web, application, and database layers.
- Application Load Balancer (ALB) configuration.
- EC2 instances for web and application servers.
- RDS instance for the database.
- Output definitions to display useful information.

### `variables.tf`

This file (if needed) contains the variable definitions used in the main configuration. (You can customize this based on your needs.)

### `outputs.tf`

This file (if separated from `main.tf`) contains the output definitions to display useful information.

## Usage Instructions

### Prerequisites

- Install [Terraform](https://www.terraform.io/downloads.html).
- Install and configure the [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html) with your credentials.

### Initial Setup

1. Clone the repository and navigate to the project directory:
   ```bash
   git clone https://github.com/yourusername/terraform-three-tier.git
   cd terraform-three-tier
   ```

2. Initialize Terraform:
   ```bash
   terraform init
   ```

### Provision the Infrastructure

1. Validate the Terraform configuration:
   ```bash
   terraform validate
   ```

2. Apply the Terraform configuration:
   ```bash
   terraform apply
   ```
   - Review the planned changes and confirm by typing `yes`.

### Verify the Setup

- Once the apply command completes, Terraform will output the DNS name of the ALB. You can navigate to this DNS name in your web browser to see the web application.

### Cleanup

To tear down the infrastructure, run:
```bash
terraform destroy
```
- Review the planned changes and confirm by typing `yes`.

## Outputs

- `vpc_id`: The ID of the VPC.
- `public_subnet_ids`: The IDs of the public subnets.
- `private_subnet_ids`: The IDs of the private subnets.
- `web_security_group_id`: The ID of the web security group.
- `app_security_group_id`: The ID of the application security group.
- `db_security_group_id`: The ID of the database security group.
- `alb_dns_name`: The DNS name of the Application Load Balancer.
- `rds_endpoint`: The endpoint of the RDS instance.

## Notes

- Ensure that you have the necessary permissions to create and manage AWS resources.
- Modify the `main.tf` file to customize the configuration as per your requirements.
- Add your SSL certificate ARN in the `aws_lb_listener` resource for HTTPS offloading.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
